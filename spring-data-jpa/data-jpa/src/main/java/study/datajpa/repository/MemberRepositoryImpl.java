package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import java.util.List;

// 사용자 정의 레포지토리 구현 클래스
// 이름은 스프링 데이터 JPA 레포지토리 인터페이스 이름 + Impl로 하면 된다(관례상).
// ex) MemberRepository 사용자 정의 레포지토리 구현 클래스 이름 : MemberRepositoryImpl

// @EnableJpaRepositories(basePackages = "study.datajpa.repository", repositoryImplementationPostfix = "Implement")
// -> 스프링 데이터 JPA 레포지토리 인터페이스 이름 + Implement
// 웬만하면 관례를 따르는 게 낫다(억지로 바꿔놓으면 다음에 이름을 찾기가 어려움, 유지보수성 감소).
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }



}
