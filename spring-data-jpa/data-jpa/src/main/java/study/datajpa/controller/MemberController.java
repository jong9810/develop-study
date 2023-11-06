package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Web 확장 - 도메인 클래스 컨버터
    // 도메인 클래스 컨버터 사용 X
    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }
    // 도메인 클래스 컨버터 사용 O
    // HTTP 요청은 회원의 id를 받지만 도메인 클래스 컨버터가 중간에 동작해서 회원 엔티티 객체를 반환해준다.
    // 도메인 클래스 컨버터도 리파지토리를 사용해서 엔티티를 찾는다.
    // 주의! 도메인 클래스 컨버터로 엔티티를 파라미터로 받으면, 이 엔티티는 단순 조회용으로만 사용해야 한다(트랜잭션이 없는 범위에서 조회했기 때문에 엔티티를 변경해도 db에 반영이 안됨).
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    // Web 확장 - 페이징과 정렬
    // 0) Page, Pageable 객체를 사용하면 웹에서 쉽게 페이징과 정렬을 할 수 있다.
    //   page : 현재 페이지, 0부터 시작한다.
    //   size : 한 페이지에 노출할 데이터 건수.
    //   sort : 정렬 조건을 정의한다(여러 번 사용 가능). ex) 정렬속성,정렬속성, ...,(ASC(생략 가능) | DESC)
    //   ex) url에 요청 파라미터 추가 : ?page=0&size=3&sort=id,desc&sort=username
    // 1) 글로벌 설정을 하려면 application.yml에 하면 된다(spring.data.web.pageable.xxx).
    // 2) 특정 메소드에만 설정하려면 @PageableDefault 어노테이션을 사용하면 된다.
    // 3) 페이징 정보가 둘 이상이면 요청 파라미터에 @Qualifier("접두사")를 추가해서 구분할 수 있다.
    //   ex) @Qualifier("member") Pageable memberPageable, @Qualifier("order") Pageable orderPageable
    //       url : /members?member_page=0&order_page=1
    // 4) page를 0부터가 아니라 1부터 시작하려면 두 가지 방법이 있다(가급적이면 페이지 인덱스는 0부터 처리하는 게 깔끔함).
    //   1) Pageable, Page를 파라미터와 응답값으로 사용하지 않고 직접 클래스를 만들어서 처리한다.
    //      그리고 직접 PageRequest(Pageable 구현체)를 생성해서 리포지토리에 넘긴다(응답값도 Page 대신 직접 만들어야 함).
    //   2) spring.data.web.pageable.one-indexed-parameters를 true로 설정한다.
    //      이 방법은 web에서 받은 page 요청 파라미터에 -1을 해서 0으로 맞춰주는 것 뿐이다.
    //      따라서 Page 객체에서는 모두 0 페이지 인덱스를 사용해야 한다.
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable) {
        // 엔티티를 직접 반환하면 절대로 안된다!!(DTO로 변환해서 반환하기)
        return memberRepository.findAll(pageable)
                .map(MemberDto::new); // 메서드 레퍼런스 : MemberDto의 new 메서드(생성자)를 호출해서 파라미터를 넘긴다.
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("member" + i, i));
        }
    }

}
