package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    //@NotEmpty
    private String name;

    @Embedded
    private Address address;

    // mappedBy가 적히면 매핑을 하지 않고 인수에 의해 매핑된 거울일 뿐이라는 뜻(읽기 전용)
    //@JsonIgnore // JSON에 포함하지 않음.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
