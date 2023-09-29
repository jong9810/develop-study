package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import jpabook.jpashop.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());

        return result;
    }

    // V2와 V3의 코드는 같지만 repository에서 로직만 다르기 때문에 repository 메서드만 고쳐주면 튜닝이 된다(정규화가 안 된 하나의 테이블).
    // 쿼리는 한 번만 날리지만, 중복 데이터가 너무 많고 페이징 처리를 할 수 없다.
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
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
                .collect(Collectors.toList());

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
                    .collect(Collectors.toList());
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

}
