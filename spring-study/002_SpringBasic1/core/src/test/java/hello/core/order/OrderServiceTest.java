package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    
    @Test
    void createOrder() throws Exception {
        //given
    	Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    /*
    @Test
    void fieldInjectionTest() {
        // orderServiceImpl에 의존관계를 주입할 방법이 없다(스프링이 아닌 환경에서) -> OrderServiceImpl에서 setter 메서드를 만들어야 가능.
        OrderServiceImpl orderService1 = new OrderServiceImpl();

        orderService1.setMemberRepository(new MemoryMemberRepository());
        orderService1.setDiscountPolicy(new FixDiscountPolicy());

        orderService1.createOrder(1L, "itemA", 10000);
    }
     */
}
