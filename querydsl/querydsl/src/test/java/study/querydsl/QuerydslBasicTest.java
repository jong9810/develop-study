package study.querydsl;

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

import static org.assertj.core.api.Assertions.*;

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

    @Test
    void startQuerydsl() {
        // When
        QMember m = new QMember("m");

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1")) // 파라미터 바인딩 처리
                .fetchOne();

        // Then
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

}
