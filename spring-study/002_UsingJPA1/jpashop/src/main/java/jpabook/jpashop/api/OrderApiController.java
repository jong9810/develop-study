package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderFlatDto;
import jpabook.jpashop.repository.order.query.OrderItemQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import jpabook.jpashop.service.query.OrderQueryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    // 최악의 경우 db 쿼리 총 11개
    // 주문 쿼리 1개 -> 결과 2건
    // 첫 번째 주문 -> 회원 1번 -> 배송 1번 -> 주문상품 1번 -> 상품 2번
    // 두 번째 주문 -> 회원 1번 -> 배송 1번 -> 주문상품 1번 -> 상품 2번
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    private final OrderQueryService orderQueryService;

    // V2와 V3의 코드는 같지만 repository에서 로직만 다르기 때문에 repository 메서드만 고쳐주면 튜닝이 된다(정규화가 안 된 하나의 테이블).
    // 쿼리는 한 번만 날리지만, 중복 데이터가 너무 많고 페이징 처리를 할 수 없다.
    @GetMapping("/api/v3/orders")
    public List<jpabook.jpashop.service.query.OrderDto> ordersV3() {
        return orderQueryService.ordersV3();
    }

    // V3를 페이징 처리 할 수 있도록 repository 수정(V3보다 성능은 조금 떨어지지만 페이징 처리 가능).
    // 데이터가 엄청나게 많은 경우에는 중복 데이터가 없는 V3.1이 더 성능이 나을 수도 있다(정규화된 상태).
    // 페이징을 해야 하는 경우에는 무조건 V3.1 사용,
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "offset", defaultValue = "100") int limit
    ) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(toList());

        return result;
    }

    // 쿼리 : 루트 1번, 컬렉션 N번 실행(1 + 2). N + 1 문제 발생.
    // ToOne 관계들을 먼저 조회하고, ToMany 관계는 각각 별도로 처리한다.
    // row 수가 증가하지 않는 ToOne 관계는 조인으로 최적화하기 쉬우므로 한 번에 조회하고,
    // ToMany 관계는 최적화하기 어려우므로 findOrderItems() 같은 별도의 메서드로 조회한다.
    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    // 쿼리 : 루트 1번, 컬렉션 1번
    // ToOne 관계들은 먼저 조회하고, 여기서 얻은 식별자 orderId로 ToMany 관계인 OrderItem 을 한꺼번에 조회.
    // Map 을 사용해서 매칭 성능 향상(O(1)).
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    // 쿼리 1번(장점)
    // 단점
    // 쿼리는 한번이지만, 조인으로 인해 DB에서 애플리케이션으로 전달하는 데이터에 중복 데이터가 추가되므로 상황에 따라 V5보다 느릴 수도 있다.
    // 애플리케이션에 추가되는 코드가 복잡하다.
    // 페이징이 불가능하다(OrderItem으로는 가능하지만, Order를 기준으로는 불가능).
    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

        return flats.stream()
                .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), toList())
                )).entrySet().stream()
                .map(e -> new OrderQueryDto(e.getKey().getOrderId(),
                        e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
                        e.getKey().getAddress(), e.getValue()))
                .collect(toList());
    }

    @Data // @Getter, @Setter, @ToString, @EqualsAndHashCode(equals, hashcode 메서드), @RequiredArgsConstructor
    static class OrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            // OrderItem 엔티티에 대한 의존도가 아직 남아있다(OrderItem도 DTO로 바꿔야 함).
            order.getOrderItems().stream().forEach(o -> o.getItem().getName());
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDto {

        private String itemName; //상품명
        private int orderPrice; //주문가격
        private int count; //주문수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

    // API 개발 고급(컬렉션) 정리
    // 1. 엔티티 조회 : 코드를 거의 수정하지 않고, 옵션만 약간 변경해서 다양한 성능 최적화를 시도할 수 있다.
    // 엔티티를 조회해서 그대로 반환(V1)
    // 엔티티 조회 후 DTO로 변환(V2)
    // 페치 조인으로 쿼리 수 최적화(V3)
    // 컬렉션 페이징 한계 돌파(V3.1)

    // 2. DTO 직접 조회 : 성능을 최적화하거나 성능 최적화 방식을 변경할 때 많은 코드를 변경해야 한다.
    // JPA에서 DTO를 직접 조회(V4)
    // 컬렉션 조회 최적화 - 일대 다 관계인 컬렉션은 in절을 활용해서 메모리에 미리 조회해서 최적화(V5)
    // 플랫 데이터 최적화 = join 결과를 그대로 조회 후 애플리케이션에서 원하는 모양으로 직접 변환(V6)

    // 권장 순서
    //  1. 엔티티 조회 방식으로 우선 접근(V2)
    //      1. 페치 조인으로 쿼리 수를 최적화(V3)
    //      2. 컬렉션 최적화
    //          1. 페이징 필요(hibernate.default_batch_fetch_size, @BatchSize 로 최적화)(V3.1)
    //          2. 페이징 필요 X(페치 조인 사용)(V3)
    //  2. 엔티티 조회 방식으로 해결이 안되면 DTO 조회 방식 사용(V4 or V5 or V6)
    //  3. DTO 조회 방식으로 해결이 안되면 NativeSQL(JPA) or 스프링 JdbcTemplate 사용

    // 참고
    // DTO 직접 조회 방식은 캐시를 사용해도 성능 최적화가 안될 정도로 트래픽이 많은 경우에 시도해보는 것을 추천한다.
    // 엔티티는 직접 캐싱을 하면 안된다(엔티티는 영속성 컨텍스트에 의해 관리되기 때문에 캐시에 잘못 올라가면 굉장히 피곤해짐).
    // 영속성 컨텍스트에서 관리하고 있는데 캐시에 올라가면 지워지지 않기 때문에 꼬일 수 있다.
    // 엔티티는 무조건 DTO로 변환을 해서 캐싱을 해야 한다.
    // 캐시 방법 : 레디스, 로컬에 메모리 캐시 등

    // 참고
    // 개발자는 성능 최적화와 코드 복잡도 사이에서 줄타기를 해야 한다(대부분의 경우).
    // 엔티티 조회 방식은 JPA가 많은 부분을 최적화해주기 때문에, 단순한 코드를 유지하면서 성능을 최적화할 수 있다.
    // DTO 직접 조회 방식은 SQL을 직접 다루는 것과 유사하기 때문에, 둘 사이에 줄타기를 해야 한다.

    // DTO 조회 방식의 선택지
    // V4 : V4는 코드가 단순하다. 특정 주문 한 건만 조회하면 이 방식을 사용해도 성능이 잘 나온다(1+N문제 발생).
    // V5 : V5는 코드가 복잡하다. 여러 주문을 한꺼번에 조회하는 경우에는 V5 방식을 사용해야 한다(1+N문제 X).
    // V6 : V6는 완전히 다른 접근방식이다. 쿼리 한 번으로 최적화되어서 상당히 좋아보이지만, Order(일대 다에서 일)를 기준으로 페이징이 불가능하다.
    //      실무에서는 페이징 처리가 필요한 경우가 많으므로 사용하기 어렵다. 데이터가 많으면 중복 전송이 증가해서 V5와 비교해서 성능 차이도 미비하다.


    // OSIV(관례상)
    // Open Session In View : 하이버네이트
    // Open EntityManager In View : JPA
    // spring.jpa.open-in-view : true(기본값)

    // true일 경우
    // 애플리케이션 실행시 경고(warn) 로그 출력됨.
    // 기본적으로 DB 트랜잭션을 시작할 때 영속성 컨텍스트가 DB 커넥션을 가져온다.
    // open-in-view가 true인 경우, 트랜잭션이 끝나도 DB 커넥션을 가지고 있다가
    //      (api의 경우) api가 유저한테 반환될 때 DB 커넥션을 반환한다.
    //      (화면의 경우) 뷰템플릿이 렌더링 될 때 DB 커넥션을 반환한다.
    // 지연 로딩은 영속성 컨텍스트가 살아있어야 하고, 영속성 컨텍스트는 기본적으로 DB 커넥션을 유지한다(장점).
    // 이 전략은 너무 오랜 시간동안 DB 커넥션 리소스를 사용하기 때문에, 실시간 트래픽이 중요한 애플리케이션에서는 커넥션이 모자랄 수 있다(장애로 이어짐).

    // false일 경우
    // 트랜잭션이 시작할 때부터 끝날 때까지만 영속성 컨텍스트, DB 커넥션이 유지된다(커넥션 리소스 낭비 X).
    // 이 경우에는 모든 지연로딩을 트랜잭션 안에서 처리해야 한다(코드 복잡도 상승, Controller/View에서 지연로딩 불가).
    // 그리고 view template에서 지연로딩이 작동하지 않는다(트랜잭션이 끝나기 전 지연로딩을 강제로 호출해두어야 함).

    // 커맨드와 쿼리 분리(OSIV 끈 상태)
    // 보통 비즈니스 로직은 특정 엔티티 몇 개를 등록하거나 수정하는 것이기 때문에 성능이 크게 문제가 되지 않는다.
    // 그런데 복잡한 화면을 출력하기 위한 쿼리는 화면에 맞춰 성능을 최적화하는 것이 중요하다(조회).
    // 하지만 그 복잡성에 비해 핵심 비즈니스에 큰 영향을 주는 것은 아니다.
    // 그렇기 때문에 크고 복잡한 애플리케이션을 개발한다면, 이 둘의 관심사가 명확하게 분리하는 선택은 유지보수 관점에서 충분히 의미있다.
    // 화면에 맞춘 기능(api)들은 자주 바뀌고 라이프사이클이 빠르다.
    // 핵심 비즈니스 로직은 화면에 비하면 잘 변경되지 않고 라이프사이클도 화면과 다르다.
    // ex) OrderService(애플리케이션이 클 경우 더 효율적임)
    //     1) OrderService : 핵심 비즈니스 로직
    //     2) OrderQueryService : 화면이나 API에 맞춘 서비스(주로 읽기전용 트랜잭션 사용)
    //     보통 서비스 계층에서 트랜잭션을 유지하기 때문에 두 서비스 모두 트랜잭션을 유지하면서 지연로딩을 사용할 수 있다.

    // OSIV 킬 경우, 유지보수성 ▲/성능 ▼ : ADMIN 처럼 커넥션을 많이 사용하지 않는 곳(admin 배포를 따로 한다면)
    // OSIV 끌 경우, 유지보수성 ▼/성능 ▲ : 고객 서비스의 실시간 API, 트래픽이 많은 경우

}
