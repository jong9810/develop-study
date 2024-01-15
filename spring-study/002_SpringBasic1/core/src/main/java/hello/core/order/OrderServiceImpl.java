package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 키워드가 붙은 모든 필드를 파라미터로 받는 생성자를 만들어줌.
public class OrderServiceImpl implements OrderService {

    // * 필드 주입(거의 안 쓰임 <- 안티 패턴, 스프링 환경이 아닌 경우 테스트하기 어려움)
    // 테스트를 스프링 컨테이너에서 하는 경우나 @Configuration 에서는 사용하기도 한다(스프링 환경인 경우에는 사용).
    /*@Autowired*/ private final MemberRepository memberRepository;
    /*@Autowired*/ private final DiscountPolicy discountPolicy;

/*
    // * 수정자 주입
    // 중간에 의존 관계를 바꾸고 싶으면 외부에서 setter를 호출해서 변경할 수 있다(거의 없음).
    @Autowired(required = false) // 선택적으로 주입 가능(주입할 대상이 없어도 오류 발생 X)
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("OrderServiceImpl.setMemberRepository");
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("OrderServiceImpl.setDiscountPolicy");
        this.discountPolicy = discountPolicy;
    }
*/
    
    // * 생성자 주입
    // OrderServiceImpl이 생성될 때 의존 관계가 같이 주입된다.
    //@Autowired // 생성자가 딱 1개만 있는 경우 @Autowired 생략 가능.
    // @Autowired는 먼저 타입이 같은 빈을 찾고 빈이 2개 이상인 경우 필드명 또는 파라미터명이 같은 빈을 찾는다.
    public OrderServiceImpl(
            MemberRepository memberRepository,
            DiscountPolicy discountPolicy
    ) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

/*
    // * 일반 메서드 주입(잘 사용하지 않음 -> 수정자나 생성자 주입으로 다 해결 가능)
    // 수정자 주입과 비슷하게 생성자가 호출된 이후에 따로 의존 관계를 주입해준다.
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    // 비즈니스 로직
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

/*
    // 테스트 용도
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
*/
}
