package study.datajpa.repository;

import study.datajpa.entity.Member;

import java.util.List;

// 사용자 정의 레포지토리 인터페이스
// : QueryDsl이나 JdbcTemplate 등 스프링 데이터 JPA가 아닌 다른 DB 접근 방법을 쓸 때 사용한다.

// 참고 : 항상 사용자 정의 레포지토리가 필요한 것은 아니다. 예를 들어 MemberQueryRepository를 스프링 빈으로 등록해서 그냥 직접 사용해도 된다(스프링 데이터 JPA가 아닌 순수 JPA).
// 참고 : 핵심 비즈니스 로직이 있는 레포지토리와 화면에 맞춘 복잡한 쿼리가 있는 레포지토리를 분리하는 것이 더 나을 수 있다.
// 애플리케이션이 커질수록 커맨드와 쿼리를 분리하는 것과 핵심 비즈니스 로직과 아닌 것을 분리하는 것, 라이프사이클에 따라 무엇을 변경해야 하는지가 달라지는 것을 복합적으로 고민해보면서 아키텍쳐(클래스, 인터페이스, 패키지)를 설계해야 한다.
public interface MemberRepositoryCustom {

    // Querydsl을 사용할 때 이 방법을 많이 사용한다.
    List<Member> findMemberCustom();
}
