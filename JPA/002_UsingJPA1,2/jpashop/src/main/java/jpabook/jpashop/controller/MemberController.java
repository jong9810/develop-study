package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        // 비어있는 MemberForm 객체를 모델에 넘기는 이유는 validation(확인)을 해주기 위해서이다.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        // 파라미터로 Member 클래스가 아니라 MemberForm을 사용한 이유?
        // Member 클래스는 화면에서 받아오는 데이터와 차이가 있기 때문에 엔터티로서의 역할만 하는 게 좋다.
        // Member 클래스에 @NotEmpty 같은 것들을 추가하게 되면 코드의 가독성, 유지보수성이 떨어진다.
        // 그렇기 때문에 화면에서 받아오는 데이터와 정확하게 일치하는 MemberForm 클래스를 새로 만들어서 사용하는 것이 좋다.

        // 참고 : 엔티티는 최대한 순수한 상태로 유지하는 것이 좋다.
        // 최대한 의존성이 없이 오직 핵심 비즈니스 로직에만 의존성이 있도록 설계하는 것이 중요하다(화면 로직은 form 객체나 dto 객체를 따로 만들기).
        // 그렇게 해야 애플리케이션이 점점 커졌을 때 엔티티를 여러 군데에서 유연하게 사용해도 유지보수성이 높아진다(확장성).

        // BindingResult : @Valid 파라미터와 함께 있을 경우 오류가 발생해도 메서드를 실행한다.
        // 오류 발생 후 members/createMemberForm.html에서 result(#fields)를 사용할 수 있다.
        // form에 이미 입력된 데이터도 그대로 다시 가져간다.
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 참고 : API를 만들 때는 이유를 불문하고 절대 Entity 객체를 API로 외부로 노출(반환)하면 안된다!!
        // Entity를 반환하게 되면 비밀번호 같은 민감한 정보를 담은 필드가 외부로 노출될 수 있다.
        // API라는 건 스펙인데, Entity에 로직(필드, 메서드 등)을 추가했는데 API의 스펙이 변해버려서 사용하기 불편해진다.

        // 여기서도 Member 엔터티를 사용하기 보다는 dto 객체를 만들어서 화면에 꼭 필요한 데이터만 뿌려주는 것이 더 좋다.
        // 템플릿 엔진에서는 어차피 서버 사이드에서 돌아가기 때문에 선택적으로 사용해도 된다.
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
