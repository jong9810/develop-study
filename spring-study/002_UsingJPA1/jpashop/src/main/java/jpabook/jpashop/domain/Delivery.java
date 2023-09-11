package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    // EnumType.ORDINARY : 컬럼에 값이 숫자로 들어가기 때문에 컬럼이 추가되면 순서가 밀려서 망할 수도 있다.
    // 따라서 enum을 사용할 때는 EnumType.STRING을 무조건 사용하자!!
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP

}
