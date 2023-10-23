package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    @Column(length = 10)
    private String city;

    @Column(length = 20)
    private String street;

    @Column(length = 5)
    private String zipcode;

    // 임베디드 값 타입을 사용하면 의미있는 비지니스 메서드를 정의하여 사용할 수 있다.
    public String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    // 필드에 직접 접근하는 경우 프록시 객체를 사용하면 equals가 제대로 동작하지 않을 수 있으므로 getter 방식으로 작성하는 것이 좋다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
