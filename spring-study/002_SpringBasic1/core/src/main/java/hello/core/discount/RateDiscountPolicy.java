package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 같은 타입의 빈이 2개 이상인 경우, @Primary 어노테이션이 붙은 빈이 선택된다.
// @Primary를 사용하면 주입을 받는 코드를 수정할 필요가 없다.
// @Primary는 기본값처럼 동작하고, @Qualifier는 매우 상세하게 동작한다.
// 스프링은 자동보다는 수동이, 넓은 범위의 선택권보다는 좁은 범위의 선택권이 우선순위가 높다.
// 그렇기 때문에 @Primary와 @Qualifier를 동시에 사용하면 @Qualifier가 우선권이 높다.

// * @Primary, @Qualifier 활용
// ex) 메인 데이터베이스 커넥션 빈을 획득할 때는 @Primary를 사용하여 @Qualifier 지정없이 편리하게 조회하고,
// 서브 데이터베이스 커넥션 빈을 획득할 때는 @Qualifier를 사용해서 이름을 명시적으로 조회하는 방법을 사용하면 코드를 깔끔하게 유지할 수 있다.
@Component
//@Qualifier("mainDiscountPolicy")
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
