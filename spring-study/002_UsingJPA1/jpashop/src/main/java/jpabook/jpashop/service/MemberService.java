package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 기본적으로 트랜잭션 안에서 데이터 변경이 이루어져야 한다!!
@RequiredArgsConstructor // final 생성자가 있는 멤버변수의 생성자만 생성해준다.
public class MemberService {

    //@Autowired
    // field injection : 멤버변수를 생성하면서 바로 의존성을 주입해준다(변경 불가).
    private final MemberRepository memberRepository; // final을 붙이면 값을 초기화하지 않았을 때 컴파일 오류를 발생시켜준다.

    /*
    @Autowired
    // setter injection : 멤버변수를 생상하면서 바로 의존성을 주입하지 않고 멤버변수를 생성한 후에 setter 메서드를 통해 의존성을 주입해준다.
    // 장점 : 테스트할 때 임시 repository를 주입해줄 수 있다.
    // 단점 : 애플리케이션이 동작하고 있을 때 setter 메서드를 호출해서 바꿀 수 있기 때문에 위험하다.
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /*
    //@Autowired // 생성자가 딱 하나만 있는 경우에는 생략해도 된다.
    // constructor injection : 멤버변수의 생성자 함수를 호출할 때 의존성을 주입해준다(딱 한번만).
    // 장점 : 테스트 케이스에서 service를 생성할 때 매개변수를 강제로 주게 할 수 있다.
    // lombok의 @RequiredArgsConstructor를 사용하면 생성자도 생략 가능하다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    // 회원 가입
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    // 조회할 때는 readOnly를 true로 주는 것이 db에 부하를 적게 준다. (default 값은 false)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
