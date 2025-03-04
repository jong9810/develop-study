package study.querydsl.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

// 꼭 MemberRepositoryImpl 이라는 이름으로 만들어 주어야 한다!
// Querydsl을 사용하려면 인터페이스에 코드를 작성할 수 없기 때문에 어쩔 수 없이 사용자 정의 리포지토리를 사용해야 한다.
// 조회 쿼리가 너무 복잡한 경우(특정한 기능에 맞춰진 조회의 경우), 사용자 정의 리포지토리(MemberRepositoryCusom) 대신에 새로운 리포지토리(MemberQueryRepository)를 만들어서 사용하는 것도 고려해보자.
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    // QueryFactory를 사용하면서 QuerydslRepositorySupport도 사용할 수 있다.
    public MemberRepositoryImpl(EntityManager em) {
        super(Member.class);
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {

        EntityManager em = getEntityManager(); // QuerydslRepositorySupport 클래스가 제공해줌.

        List<MemberTeamDto> result = from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username.as("username"),
                        member.age.as("age"),
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .fetch();

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username.as("username"),
                        member.age.as("age"),
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .fetch();
    }

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username.as("username"),
                        member.age.as("age"),
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    // 리포지토리 지원 - QueryRepositorySupport
    // * 장점
    // 1) getQuerydsl().applyPagination() : 스프링 데이터가 제공하는 페이징을 Querydsl로 편리하게 변환할 수 있다(단, Sort는 오류 발생!).
    // 2) from()으로 시작할 수 있다(최근에는 QueryFactory를 사용해서 select()로 시작하는 것이 더 명시적임).
    // 3) EntityManager을 제공해준다.
    // * 단점
    // 1) Querydsl 3.x버전을 대상으로 만들어졌다.
    // 2) Querydsl 4.x버전에 나온 JPAQueryFactory로 시작할 수 없다(select()로 시작할 수 없음, from()으로 시작해야함).
    // 3) QueryFactory를 제공하지 않는다.
    // 4) 스프링 데이터 Sort 기능이 제대로 동작하지 않는다.
    public Page<MemberTeamDto> searchPageSimple2(MemberSearchCondition condition, Pageable pageable) {
        JPQLQuery<MemberTeamDto> jpaQuery = from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username.as("username"),
                        member.age.as("age"),
                        team.id.as("teamId"),
                        team.name.as("teamName")));

        JPQLQuery<MemberTeamDto> query = getQuerydsl().applyPagination(pageable, jpaQuery);

        QueryResults<MemberTeamDto> results = query.fetchResults();

        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    // 카운트 쿼리를 최적화하고 싶을 때 searchPageComplex 처럼 카운트 쿼리를 직접 작성할 수 있다(데이터가 많아서 최적화가 꼭 필요한 경우에만 사용).
    // ex) 카운트 쿼리를 먼저 실행하고, 데이터 개수가 0개이면 컨텐트 쿼리를 실행하지 않는다.
    // count 쿼리를 생략 가능한 경우
    // 1) 페이지 시작이면서 컨텐츠 사이즈가 페이지 사이즈보다 작을 때(페이지가 1개인 경우)
    // 2) 마지막 페이지일 때(offset + 컨텐츠 사이즈를 하면 전체 사이즈가 나옴)
    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username.as("username"),
                        member.age.as("age"),
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()));

        //return new PageImpl<>(content, pageable, total);

        // 카운트 쿼리가 생략 가능한 경우 카운트 쿼리를 생략한다.
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    // 참고 : 정렬(Sort)은 조건이 조금만 복잡해져도 Pageable의 Sort 기능을 사용하기 어렵다.
    //       루트 엔티티 범위를 벗어나는(join을 하는) 동적 정렬 기능이 필요하면 스프링 데이터 페이징이 제공하는 Sort를 사용하기 보다는 파라미터를 받아서 직접 처리하는 것을 권장한다.

}
