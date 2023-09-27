package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
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
    public List<SimpleOrderDto> ordersV2() {
        /*
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
        */

        return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        // Dto 내부에서 엔티티를 받는 것은 상관없다(중요하지 않은 곳에서 중요한 것을 사용하는 것이기 때문).
        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName(); // LAZY 초기화 : 영속성 컨텍스트가 id를 가지고 찾아본 후 없으면 db 쿼리를 날리는 것.
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress(); // LAZY 초기화
        }
    }

}
