package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Qualifier는 모든 의존 관계 주입 방식(생성자, 수정자, 필드 등)에 사용할 수 있다.
// @Qualifier는 빈을 직접 등록할 때도 사용할 수 있다.
// @Qualifier("main")은 빈의 이름을 바꾸는 것이 아니라 @Qualifier("main")이 붙어있는 빈을 찾는 추가적인 방법을 제공하는 것이다.
// @Qualifier("main")은 @Qualifier("main")을 찾지 못하면 "main"이라는 스프링 빈을 찾는다(예상하지 못한 결과 -> @Qualifier를 찾는 용도로만 사용하는 것이 명확함).
// @Qualifier("main")이 없고 "main" 스프링 빈도 없을 경우에는, NoSuchBeanDefinitionException 예외를 발생시킨다.
// @Qualifier의 단점은 주입을 받는 모든 코드에 @Qualifier를 붙여주어야 한다는 것이다.
@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
