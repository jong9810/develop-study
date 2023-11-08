package study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    // 동시성 문제 없이 사용할 수 있도록 설계되어 있기 때문에 필드로 빼서 사용하는 것을 권장한다(EntityManager 자체가 멀티 스레드 환경에서 사용할 수 있음).
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    void startJPQL() {
        // When
    	// member1을 찾기
        String qlString =
                "select m from Member m " +
                "where m.username = :username";
        
        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        // Then
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // Querydsl은 JPQL의 빌더 역할을 한다.
    // Querydsl로 생성된 JPQL을 보고 싶으면 application 파일에 spring.jpa.properties.hibernate.use_sql_comments를 true로 설정해주면 된다.
    @Test
    void startQuerydsl() {
        // When
        // Q-Type을 선언해서 사용할 수도 있지만 static import를 해서 사용하는 것을 권장한다.
        // 같은 테이블을 조인해야 하는 경우에는 alias가 같으면 안되기 때문에 new 키워드로 선언해서 사용해야 한다.
        //QMember m1 = new QMember("m1");

        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1")) // 파라미터 바인딩 처리
                .fetchOne();

        // Then
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 검색 조건 쿼리
    // and(조건) [and], or(조건) [or]
    // eq(10) [=], ne(10) [!=], eq(10).not() [!=]
    // isNotNull() [is not null]
    // in(10, 20, 30) [in], notIn(10, 20, 30) [not in], between(10, 30) [between]
    // goe(30) [>=], gt(30) [>], loe(30) [<=], lt(30) [<]
    // like("member%") [like], contains["member"] [like "%member%"], startsWith("member") [like "member%"]
    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.between(10, 30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
        assertThat(findMember.getAge()).isEqualTo(10);
    }

    // and 연산은 ,로 이어붙일 수도 있다(and()로 이어붙이는 것보다 깔끔).
    // ,로 이어붙일 때는 중간에 null이 들어가면 무시한다(동적쿼리에서 많이 사용).
    @Test
    public void searchAndParam() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
        assertThat(findMember.getAge()).isEqualTo(10);
    }

    // 결과 조회
    // fetch() : 리스트 조회, 데이터가 없으면 빈 리스트를 반환한다.
    // fetchOne() : 단건 조회.
    //   1) 결과가 없으면 null을 반환한다.
    //   2) 결과가 둘 이상이면 com.querydsl.core.NonUniqueResultException을 터뜨린다.
    // fetchFirst() : limit(1).fetchOne(), 조회 결과 중 제일 첫 번째 결과를 반환한다.
    
    // @Deprecated된 fetch 메서드
    // fetchResults() : 페이징 정보를 포함한다. total count 쿼리도 추가로 실행한다(복잡한 쿼리인 경우 fetchResults()를 사용하지 말고 total count 쿼리를 따로 날려야 함).
    // fetchCounts() : count 쿼리로 변경해서 count 수를 조회한다.
    @Test
    public void resultFetch() {
        /*
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        Member fetchOne = queryFactory
                .selectFrom(member)
                .fetchOne();

        Member fetchFirst = queryFactory
                .selectFrom(member)
                .fetchFirst();

        QueryResults<Member> fetchResults = queryFactory
                .selectFrom(member)
                .fetchResults();
        fetchResults.getTotal();
        fetchResults.getLimit();
        fetchResults.getOffset();
        List<Member> content = fetchResults.getResults();
         */

        long fetchCount = queryFactory
                .selectFrom(member)
                .fetchCount();

    }

}
