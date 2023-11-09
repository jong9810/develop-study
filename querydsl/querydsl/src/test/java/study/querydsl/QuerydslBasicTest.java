package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.*;

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

    // 정렬
    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 오름차순(asc)
     * 단, 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
     */
    @Test
    void sort() {
        //given
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        //when
        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        //then
        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    // 페이징
    // offset(1) : 0번부터 시작하기 때문에 1을 하면 두 번째 행부터 반환한다.
    // limit(2) : offset부터 2개의 행을 결과로 가져온다.
    @Test
    void paging1() {
        //when
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void paging2() {
        //when
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        //then
        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }

    // 집합
    // querydsl에서 count, sum, avg, max, min 등 집합 함수를 지원한다.
    // 집합 함수의 조회 결과는 querydsl의 Tuble로 반환받는다(Tuple은 여러 타입의 결과를 조회할 때 사용함).
    // 실무에서는 Tuple 대신 DTO를 만들어서 결과를 반환받는다.
    @Test
    void aggregation() {
        //when
        List<Tuple> result = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()

                )
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);

        //then
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구하라.
     */
    @Test
    void group() {
        //when
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        //then
        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    /**
     * 나이가 35이상인 회원의 이름을 조회하라.
     */
    @Test
    void having() {
        //when
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .groupBy(member.age)
                .having(member.age.goe(35))
                .fetch();

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("member4");
    }

    // 조인
    //1. 기본 조인 : join(), innerJoin(), leftJoin(), rightJoin() 등
    /**
     * 예) teamA에 소속된 모든 회원을 조회하라.
     */
    @Test
    void join() throws Exception {
        //when
        List<Member> result = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        //then
        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    // 세타 조인 : 연관 관계가 없는 테이블을 조인하는 것.
    // 세타 조인을 사용하면 내부 조인은 가능하지만 외부 조인은 불가능하다(조인 on을 사용하면 가능).
    /**
     * 예) 회원의 이름이 팀 이름과 같은 회원을 조회하라.
     */
    @Test
    void theta_join() throws Exception {
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        //when
        List<Member> result = queryFactory
                .select(member)
                .from(member, team) // 카테시안 곱(cross join)
                .where(member.username.eq(team.name))
                .fetch();

        //then
        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

    // 2. ON 절 조인
    // 1) 조인 대상을 필터링해준다.
    // 참고 : on 절을 활용해 조인 대상을 필터링할 때, 외부조인이 아니라 내부조인을 사용하면 where 절을 사용해서 필터링하는 것과 동일하다.
    //       그렇기 때문에 on 절을 활용한 조인 대상 필터링은 외부조인에서만 사용하는  것을 권장한다(내부조인은 익숙한 where절로 처리).
    /**
     * 예) 회원과 팀을 조인하면서 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회하기
     * JPQL : select m, t from member m left join m.team t on t.name = 'teamA'
     */
    @Test
    void join_on_filtering() throws Exception {
        //when
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA")) // 외부 조인 on 절
                //.join(member.team, team).where(team.name.eq("teamA")) // 내부 조인 on 절
                //.join(member.team, team).on(team.name.eq("teamA")) // 내부 조인 where 절
                .fetch();

        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    // 2) 연관 관계가 없는 엔티티끼리 외부 조인이 가능하다.
    // 주의! 연관 관계가 없는 엔티티의 외부 조인은 leftJoin() 부분에 일반 조인과 다르게 엔티티가 하나만 들어간다.
    //      * leftJoin() 안에 엔티티 두 개 : 두 엔티티의 식별자와 외래키가 같은 것을 필터링한 후 on 절 필터링 적용.
    //      * leftJoin() 안에 엔티티 한 개 : on 절 필터링만 적용.
    /**
     * 예) 회원의 이름과 팀의 이름이 같은 대상을 외부 조인해서 조회하라.
     */
    @Test
    void join_on_no_relation() throws Exception {
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        //when
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name)) // 막 조인이기 때문에 leftJoin()에 team만 넣어준다(식별자 같은 것을 조회하지 않음).
                .fetch();

        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    // 3. 페치 조인
    @PersistenceUnit
    EntityManagerFactory emf;
    @Test
    void no_fetch_join() throws Exception {
        //given
    	em.flush();
        em.clear();
                
        //when
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());// 찾은 회원의 team 필드가 로딩이 되었는지 안되었는지 boolean으로 반환.

        //then
        assertThat(loaded).as("페치 조인 미적용").isFalse();
    }

    @Test
    void use_fetch_join() throws Exception {
        //given
        em.flush();
        em.clear();

        //when
        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin() // 그냥 조인 문법에다가 .fetchJoin()만 붙여주면 된다.
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());// 찾은 회원의 team 필드가 로딩이 되었는지 안되었는지 boolean으로 반환.

        //then
        assertThat(loaded).as("페치 조인 적용").isTrue();
    }

    // 서브 쿼리(com.querydsl.jpa.JPAExpressions 사용)
    // 1. where 절 서브 쿼리
    /**
     * 예) 나이가 가장 많은 회원을 조회하라.
     */
    @Test
    void subQueryEq() throws Exception {
        //given
        QMember memberSub = new QMember("memberSub");

        //when
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        //then
        assertThat(result)
                .extracting("age")
                .containsExactly(40);
    }

    /**
     * 예) 나이가 평균 이상인 회원을 조회하라.
     */
    @Test
    void subQueryGoe() throws Exception {
        //given
        QMember memberSub = new QMember("memberSub");

        //when
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        //then
        assertThat(result)
                .extracting("age")
                .containsExactly(30, 40);
    }

    /**
     * 예) 나이가 10살 보다 많은 회원을 서브쿼리로 조회하라(효율적이지 않은 쿼리).
     */
    @Test
    void subQueryIn() throws Exception {
        //given
        QMember memberSub = new QMember("memberSub");

        //when
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))
                ))
                .fetch();

        //then
        assertThat(result)
                .extracting("age")
                .containsExactly(20, 30, 40);
    }

    // 2. select 절 서브 쿼리
    /**
     * 예) 모든 회원 나이의 평균과 이름을 조회하라.
     */
    @Test
    void selectSubQuery() throws Exception {
        //given
        QMember memberSub = new QMember("memberSub");

        //when
        List<Tuple> result = queryFactory
                .select(member.username,
                        select(memberSub.age.avg())
                                .from(memberSub)
                )
                .from(member)
                .fetch();

        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    // 참고 : JPQL은 from 절 서브 쿼리를 지원하지 않기 때문에 Querydsl도 안된다.
    // from 절 서브 쿼리 해결방안
    // 1) 서브 쿼리를 join으로 변경한다(불가능할 수도 있음, 대부분 가능).
    // 2) 애플리케이션에서 쿼리를 2번 분리해서 실행한다.
    // 3) nativeSQL을 사용한다(1, 2번 방법이 안되면 사용하자).

    // 참고 : db는 데이터만 필터링해서 가져오는 용도로 사용하고, 로직들은 애플리케이션이나 화면에서 푸는 것을 고려해보자(from 절 서브 쿼리를 줄일 수 있음).
    //       데이터를 화면에 딱 맞게 가져오려고 하면 SQL이 너무 복잡해져서 재사용성이 떨어질 수 있다.

    // 참고 : 한 번의 쿼리로 모든 데이터를 가져오려고만 하지 말고 복잡한 쿼리는 여러 번 나누어서 실행하는 것도 고려해보자(무조건 한 방 쿼리가 좋은 것은 아님, SQL AntiPatterns 책 참고).


    // Case 문(select, where 절에서 사용 가능)
    // 정말 case문을 써야하는지는 고민해보아야 한다(db에서는 데이터를 가져오는 용도로만 사용(필터링, 그룹핑, 계산 정도만)).
    // 1. 단순한 조건
    @Test
    void basicCase() throws Exception {
        //when
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();


        //then
        for (String age : result) {
            System.out.println("age = " + age);
        }
    }
    
    // 2. 복잡한 조건
    @Test
    void complexCase() throws Exception {
        //when
        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    // 상수 사용하기(Expressions.constant() 사용)
    @Test
    void constant() throws Exception {
        //when
        List<Tuple> result = queryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    // 문자 더하기(concat(), stringValue() 등 사용)
    // stringValue() : 문자가 아닌 다른 타입의 데이터를 문자로 변환한다.
    // enum 타입은 값이 안 나오는데, 그 때 stringValue()를 사용하면 된다.
    @Test
    void concat() throws Exception {
        //when
        // username_age
        List<String> result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    // 프로젝션 결과 반환 - 기본
    // 1. 프로젝션 대상이 하나 : 타입을 명확하게 지정할 수 있다.
    @Test
    void simpleProjection() throws Exception {
        //when
        List<String> result = queryFactory
                .select(member.username) // member 객체를 넣어도 프로젝션 대상이 하나인 것처럼 동작한다.
                .from(member)
                .fetch();

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    // 2. 프로젝션 대상이 둘 이상 : 튜플이나 DTO로 조회해야 한다.
    // 1) 튜플로 조회
    // * 튜플을 리포지토리 계층에서 사용하는 것은 괜찮지만 서비스나 컨트롤러 계층까지 가지고 가는 것은 좋은 설계가 아니다(튜플이 com.querydsl 하위 클래스이기 때문).
    // * 리포지토리 외의 계층에서 사용해야 하는 경우 DTO로 변환해서 반환하는 것이 좋다.
    @Test
    void tupleProjection() throws Exception {
        //when
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        //then
        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            System.out.print("username = " + username + ", ");
            Integer age = tuple.get(member.age);
            System.out.println("age = " + age);
        }
    }

    // 2-0) DTO로 조회(JPQL)
    // 순수 JPA에서는 DTO를 조회할 때 new 명령어를 사용해야 한다.
    // DTO의 패키지 이름을 다 적어주어야 한다.
    // 생성자 방식만 지원한다.
    @Test
    void findDtoByJPQL() throws Exception {
        //when
        List<MemberDto> result = em.createQuery("select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        //then
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    // Querydsl은 결과를 조회할 때 프로퍼티 접근, 필드 직접 접근, 생성자 사용의 세 가지 방법을 지원한다.

    // 2-1) DTO로 조회(Querydsl - Setter, Projections.bean())
    // DTO에 getter, setter, 기본 생성자가 있어야 한다.
    // 필드 이름과 getter, setter 이름을 보고 값을 넣어준다.
    @Test
    void findDtoBySetter() throws Exception {
        //when
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        //then
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    // 2-2) DTO로 조회(Querydsl - Field, Projections.fields())
    // DTO에 getter, setter가 없어도 필드에 직접 값을 넣어준다(기본 생성자는 필요함).
    // DTO 필드 이름과 테이블 필드 이름이 같아야 한다(다른 경우 테이블에 alias 주어야 함).
    @Test
    void findDtoByField() throws Exception {
        //when
        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        //then
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    // 2-3) DTO로 조회(Querydsl - Constructor, Projections.constructor())
    // DTO에 getter, setter가 없어도 생성자를 활용해서 값을 넣어준다(생성자와 조회하는 데이터의 값 타입, 순서가 일치해야 함).
    // 생성자 조회는 필드 이름이 아니라 값 타입으로 구분한다.
    @Test
    void findDtoByConstructor() throws Exception {
        //when
        List<UserDto> result = queryFactory
                .select(Projections.constructor(UserDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();

        //then
        for (UserDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    // 참고 : DTO 필드 이름과 엔티티 필드 이름이 다른 경우 DTO 필드 이름을 as() 안에 넣어주면 된다.
    // 참고 : 서브 쿼리를 사용해서 DTO 필드에 값을 넣어주고 싶은 경우에는 ExpressionUtils.as()를 사용한다.
    //       첫 번째 파라미터에 서브 쿼리(JPAExpressions), 두 번쨰 파라미터에 값을 넣어줄 DTO 필드 이름을 문자열로 넣어주면 된다.
    @Test
    void findUserDto() throws Exception {
        //given
        QMember memberSub = new QMember("memberSub");

        //when
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),
                        //ExpressionsUtils.as(member.username, "name"), // 바로 윗줄 코드와 동일한 기능.

                        ExpressionUtils.as(JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub), "age"
                        )
                ))
                .from(member)
                .fetch();

        //then
        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    // 3) @QueryProjection
    // * 장점
    // 1) DTO 형식에 맞지 않는 경우 컴파일 오류를 낸다(생성자 조회 방식은 런타임 오류 발생).
    // 2) 생성자 호출을 보장해준다.
    // * 단점
    // 1) DTO의 Q파일을 생성해야 한다.
    // 2) @QueryProjection 어노테이션을 DTO 파일에 정의함으로써 DTO가 Querydsl에 의존성을 가지게 된다(Querydsl을 사용하지 않으면 에러 발생, DTO는 리포지토리 외의 계층에도 사용함).
    @Test
    void findDtoByQueryProjection() throws Exception {
        //when
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();

        //then
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    // 참고 : distinct는 querydsl에서 .distinct() 메서드를 필드 뒤에 붙이면 된다.
    

    // 동적 쿼리
    // 1. BooleanBuilder 사용
    @Test
    void dynamicQuery_BooleanBuilder() throws Exception {
        //given
        String usernameParam = "member1";
        Integer ageParam = 10;

        //when
        List<Member> result = searchMember1(usernameParam, ageParam);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        // 생성자 함수에 초기값으로 필수 조건을 작성할 수도 있다.
        BooleanBuilder builder = new BooleanBuilder();

        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    // 2. Where 다중 파라미터 사용!!(실무에서 많이 사용됨)
    // * 장점
    // 1) 쿼리 자체의 가독성이 높아진다(where()에서 메서드 이름으로 어떤 조건인지 확인이 가능함).
    // 2) 조건을 메서드로 빼기 때문에 조건을 다른 쿼리에서 재사용할 수 있다.
    // 3) 여러 조건을 조합할 수 있다(null 체크는 주의해야 함).
    // * 단점 : 조건을 메서드로 빼는 것이 번거롭다.
    @Test
    void dynamicQuery_WhereParam() throws Exception {
        //given
        String usernameParam = "member1";
        Integer ageParam = 10;

        //when
        List<Member> result = searchMember2(usernameParam, ageParam);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
                //.where(usernameEq(usernameCond), ageEq(ageCond))
                .where(allEq(usernameCond, ageCond))
                .fetch();
    }

    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond != null ? member.username.eq(usernameCond) : null; // where()에 null이 들어가면 무시한다.
    }

    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

    // 여러 조건을 조합하여 하나의 메서드로 만들 수도 있다(Predicate 대신 BooleanExpression을 반환값으로 사용해야 함).
    // 예) 광고 상태가 isValid, 날짜가 In이면 isServiceable.
    private BooleanExpression allEq(String usernameCond, Integer ageCond) {
        return usernameEq(usernameCond).and(ageEq(ageCond));
    }

    // 수정, 삭제 배치 쿼리(벌크 연산)
    // 쿼리 한 번으로 대량의 데이터를 수정, 삭제할 때 사용한다.

    // 주의! 벌크 연산은 영속성 컨텍스트를 무시하고 db에 바로 쿼리를 날리기 때문에 영속성 컨텍스트와 db의 데이터가 안 맞을 수 있다.
    // * 해결법
    // 1) 영속성 컨텍스트가 완전히 비어있는 상태에서 벌크 연산을 수행한다(잘 안 쓰임).
    // 2) 영속성 컨텍스트가 비어있지 않은 상태라면, 벌크 연산을 수행하고 나서 영속성 컨텍스트를 완전히 초기화해준다(em.flush(), em.clear(), 실무에서 많이 쓰임).
    @Test
    //@Commit
    void bulkUpdate() throws Exception {
        //when
        /*
        1. member1 = 10 -> db: member1
        2. member2 = 20 -> db: member2
        3. member3 = 30 -> db: member3
        4. member4 = 40 -> db: member4
        */

        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        /*
        1. member1 = 10 -> db: 비회원, 영속성 컨텍스트: member1
        2. member2 = 20 -> db: 비회원, 영속성 컨텍스트: member2
        3. member3 = 30 -> db: member3, 영속성 컨텍스트: member3
        4. member4 = 40 -> db: member4, 영속성 컨텍스트: member4
        */

        em.flush();
        em.clear();

        // Repeatable Read(db 조회 결과보다 영속성 컨텍스트의 값이 우선임)
        // 영속성 컨텍스트를 초기화하지 않으면,
        // db에서 가져올 때는 "비회원"이 포함된 결과를 가져오지만, 영속성 컨텍스트에 이미 있는 인스턴스인 경우에는 db에서 가져온 것을 버리고 현재 값을 유지한다.
        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }
    }

    // 기존 숫자에 더하기 벌크 연산
    // 빼기를 하려면 add(-1)로 하면 된다.
    @Test
    void bulkAdd() throws Exception {
        //when
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
    }

    // 기존 숫자에 곱하기 벌크 연산
    @Test
    void bulkMultiply() throws Exception {
        //when
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.multiply(2))
                .execute();
    }
    
    // 삭제 벌크 연산
    @Test
    void bulkDelete() throws Exception {
        //when
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }

    // SQL function 호출하기(Expressions.stringTemplate())
    // 문자인 경우 stringTemplate()을 쓰고 숫자나 다른 타입인 경우는 numberTemplate() 등을 사용하면 된다.
    @Test
    void sqlFunction() throws Exception {
        //when
        List<String> result = queryFactory
                .select(Expressions.stringTemplate(
                        "function('replace', {0}, {1}, {2})",
                        member.username, "member", "M"))
                .from(member)
                .fetch();

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
    
    @Test
    void sqlFunction2() throws Exception {
        //when
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                // lower 같은 간단한 함수는 querydsl이 어느 정도 지원해준다(ANSI 표준은 거의 제공됨).
                //.where(member.username.eq(Expressions.stringTemplate("function('lower', {0})",member.username)))
                .where(member.username.eq(member.username.lower()))
                .fetch();

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

}
