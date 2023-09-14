package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

// spring boot를 띄운 상태로 테스트를 할 경우 @ExtendWith(SpringExtension.class) @SpringBootTest 를 써야한다.
@ExtendWith(SpringExtension.class) 
@SpringBootTest
@Transactional // 테스트 클래스에 붙이면 테스트가 종료되면 Rollback 한다.
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception {
        //given(주어지는 것)
        Member member = new Member();
        member.setName("kim");

        //when(조건)
        Long savedId = memberService.join(member);

        //then(결과)
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2); // 예외 발생!
        });
        //fail("예외가 발생해야 한다."); // 이 코드에 도달하면 잘못된 테스트
    }

}