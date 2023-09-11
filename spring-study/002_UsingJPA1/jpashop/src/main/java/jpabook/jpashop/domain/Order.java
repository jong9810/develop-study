package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    // 모든 연관관계에서 즉시로딩(fetch = FetchType.EAGER)은 절대 사용하면 안된다!!!
    // XToMany : 기본이 LAZY(지연로딩)
    // XToOne : 기본이 EAGER(즉시로딩), 따라서 명시적으로 지연로딩(LAZY)로 바꿔 주어야 한다.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 컬렉션은 필드에서 초기화하는 것이 안전하다(null문제 X).
    // 하이버네이트가 엔티티를 영속화할 때, 컬렉션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다.
    // 따라서 컬렉션 필드는 처음 객체를 생성할 때 초기화를 한 후에 변경을 하지 않고 있는 그대로 사용하는 것이 가장 좋다.
    //
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 1대 1 관계에서는 외래키를 어느 테이블에 정의해도 상관없지만 장단점이 있다.
    // 주로 액세스를 자주 하는 테이블에 정의한다.
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // 테이블에서 order_date로 컬럼명이 지정된다.
    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 : [ORDER, CANCEL] 두 가지 상태 존재


    //== 연관관계 편의 메서드 ==//
    // 관계가 양방향일 때 사용하면 좋다.
    // 연관관계 편의 메서드는 핵심적으로 컨트롤 하는 쪽(액세스가 잦은 곳?)에 정의하는 것이 좋다.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
