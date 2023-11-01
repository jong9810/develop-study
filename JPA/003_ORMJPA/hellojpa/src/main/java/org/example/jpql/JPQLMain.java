package org.example.jpql;


import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JPQLMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Team teamC = new Team();
            teamC.setName("teamC");
            em.persist(teamC);

            Member member1 = new Member();
            member1.setUsername("회원1");

            Member member2 = new Member();
            member2.setUsername("회원2");

            Member member3 = new Member();
            member3.setUsername("회원3");

            Member member4 = new Member();
            member4.setUsername("회원4");

            member1.changeTeam(teamA);
            member2.changeTeam(teamA);
            member3.changeTeam(teamB);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);

            em.flush();
            em.clear();

            // 벌크 연산
            int resultCnt = em.createQuery("update Member m set m.age = :age")
                    .setParameter("age", 20)
                    .executeUpdate();

            System.out.println("resultCnt = " + resultCnt);

            // 현재는 영속성 컨텍스트에 update 쿼리가 반영되어 있지 않다(회원의 나이가 모두 0).
            // 그렇기 때문에 영속성 컨텍스트를 한 번 초기화해주어야 db와 영속성 컨텍스트의 정합성이 맞아 떨어진다.
            em.clear();
            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember.getAge() = " + findMember.getAge());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }

}
