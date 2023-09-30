package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문 
     */
    // Controller가 아니라 Service에서 order 로직을 구현해놓으면 유지보수성도 올라가고 컨트롤러가 지저분해지는 것도 막을 수 있다.
    // @Transactional에서 JPA가 가장 깔끔하게(영속상태로) 동작하기 때문에 Controller에서는 식별자만 넘기고 Service에서 JPA를 사용하는 것이 좋다.
    // Test를 구현할 때도 이렇게 해놓으면 편리하다.
    // 받아온 식별자로 엔티티를 찾아오는 것부터 하면 엔티티들도 영속 상태로 흘러가기 때문에 엔티티의 값도 바꿀 수 있어서 좋다(조회는 상관 없음).
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        // 기존 mybatis를 사용할 때는 주문 취소를 하면 update sql문을 만들어서 각각의 테이블에 쿼리를 보내야 했다.
        // jpa는 변경 내역 감지(dirty checking)를 통해 변경된 값을 자동으로 update 해준다.
        order.cancel();
    }

    /**
     * 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }

}
