package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원 조회 API
     */
    @GetMapping("/api/v1/members")
    // 문제점
    // 1) 기본적으로 엔티티의 모든 값이 노출된다.
    // 2) 응답 스펙을 맞추기 위해 엔티티에 로직이 추가된다(@JsonIgnore, 별도의 뷰 로직 등).
    // 3) 실무에서는 같은 엔티티에 대해 API가 용도에 따라 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 프레젠테이션 응답 로직을 담기는 어렵다.
    // 4) 엔티티가 변경되면 API 스펙이 변한다.
    // 5) 추가로 컬렉션(array 등)을 직접 반환하면 향후 API 스펙을 변경하기 어렵다(별도의 Result 클래스 생성으로 해결).
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    // 노출하고 싶은 데이터만 노출할 수 있다.
    // API 스펙이 Member 엔티티가 아니라 MemberDto와 1대 1로 매핑된다(엔티티 변경되어도 API 스펙은 안 변함).
    // 유지보수하기도 v1보다 v2가 훨씬 쉽다.
    // JSON이 Array가 아니라 객체로 생성되어서 유연성이 높아짐.
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    /**
     * 회원 등록 API
     */
    @PostMapping("/api/v1/members")
    // @RequestBody : JSON 데이터를 Member에 바인딩 시켜줌.
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    /**
     * 회원 수정 API
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMembreRequest request
    ) {
        // 커맨드와 쿼리를 분리하는 스타일(유지보수성 향상).
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMembreRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    // lombok : 엔티티에는 @Getter 정도만 사용하고, Dto에는 아무거나 다 써도 상관 없다.
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

}
