package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    // 양방향 연관관계이기 때문에 무한 루프에 빠지게 된다.
    // 해결하기 위해서는 한 쪽에 @JsonIgnore를 해주면 되지만 권장하지 않는다.
    // 지연 로딩인 경우에는 Proxy 기술을 사용하기 때문에 ByteBuddyInterceptor 객체가 대신 들어가게 된다.
    // 지연 로딩(LAZY)을 피하기 위해 즉시 로딩(EAGER)으로 설정하면 안된다!
    // 즉시 로딩 때문에 연관관계가 필요 없는 경우에도 데이터를 항상 조회해서 성능 문제가 발생할 수 있다.
    // 항상 지연 로딩을 기본으로 하고, 성능 최적화가 필요한 경우에는 페치 조인(fetch join)을 사용하자!
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        /*
        // 필요한 데이터만 API 스펙에 노출하는 것이 협업하는 데에도 도움이 된다.
        for (Order order : all) {
            order.getMember().getName(); // Lazy가 강제 초기화됨.
            order.getDelivery().getAddress(); // Lazy가 강제 초기화됨.
        }
        */
        return all;
    }

    // 원래는 List로 반환하지 말고 Result로 한 번 감싸서 반환해야 한다(컬렉션 -> JSON array 문제 때문에).
    // V1, V2 모두 지연 로딩으로 인해서 db 쿼리가 너무 많이 호출되는 문제가 있다.
    // 하나의 주문 당 2번(Member, Delivery 조회)의 쿼리가 실행된다.
    // Order -> SQL 1번 -> 결과 주문 수 2개
    // 1번째 주문 -> Member, Delivery 조회
    // 2번째 주문 -> Member, Delivery 조회
    // 총 5번의 db 쿼리 실행됨(N+1, 1+N 문제).
    // 주문 1 + 회원 N + 배송 N(최악의 경우)
    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleDto> ordersV2() {
        /*
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
        */

        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(OrderSimpleDto::new)
                .collect(toList());
    }

    // V2와 V3는 로직은 똑같지만 쿼리(fetch join)가 달라짐으로써 성능이 향상되었다.
    // V3의 문제점은 Order, Member, Delivery의 모든 컬럼 값을 가져온다는 것이다.
    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<OrderSimpleDto> result = orders.stream()
                .map(o -> new OrderSimpleDto(o))
                .collect(toList());
        return result;
    }

    // V4에서는 JPA에서 Dto로 바로 조회를 해서 원하는 컬럼 값만 가져온다(V3의 문제 해결).
    // V3와 V4는 둘 간의 우열을 가리기가 어렵다(장단점이 있음).
    // V3는 모든 컬럼 값을 가져오기 때문에 재사용 성이 높지만 네트워크를 좀 더 많이 사용한다.
    // V4는 원하는 컬럼 값만 가져와서 네트워크를 좀 덜 사용하지만(성능이 쬐끔 더 좋지만) 특정 컬럼 값만 가져오기 때문에 재사용성이 낮다.
    // V4는 repository 코드가 V3보다 좀 더 지저분하다.
    // V3와 V4를 선택할 때는 성능 테스트를 해보고 성능 차이가 심한 경우 V4를 사용하고, 성능 차이가 적으면 V3를 사용하여 재사용성을 높이는 것이 좋다.
    // 대부분의 경우에는 성능 차이가 심하지 않아서 V3를 가장 많이 사용한다(성능은 join이나 where에서 크게 차이가 남).
    // 컬럼이 엄청나게 많거나 자주 조회되는 쿼리같은 경우에는 V4를 사용하는 것도 고려를 해봐야 한다.
    // V4같이 성능에만 몰빵한 쿼리를 저장하는 패키지(ex. repository.order.simplequery)를 repository 하위에 생성한다.
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class OrderSimpleDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public OrderSimpleDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName(); // LAZY 초기화 : 영속성 컨텍스트가 id를 가지고 찾아본 후 없으면 db 쿼리를 날리는 것.
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress(); // LAZY 초기화
        }
    }

    // 쿼리 방식 선택 권장 순서
    // 1) 우선 엔티티를 DTO로 변환하는 방법을 선택한다(V2).
    // 2) 필요하면 페치 조인으로 성능을 최적화한다(V3). -> 대부분의 성능 이슈가 해결됨(1+N 문제).
    // 3) 그래도 안되면 DTO로 직접 조회하는 방법을 사용한다(V4).
    // 4) 최후의 방법은 JPA가 제공하는 네이티브 SQL이나 스프링 JDBC Template을 사용해서 SQL을 직접 사용한다.

}
