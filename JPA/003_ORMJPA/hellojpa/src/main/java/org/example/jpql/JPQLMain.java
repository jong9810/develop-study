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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setAge(10);
            member2.setType(MemberType.ADMIN);

            member.changeTeam(team);
            member2.changeTeam(team);

            em.persist(member);
            em.persist(member2);

            em.flush();
            em.clear();

            // 상태 필드
//            String query = "select m.username from Member m";

            // 단일 값 연관 경로
            // 묵시적 내부 조인 발생(중요!! 조심해서 사용해야 함. 웬만하면 사용하지 말자)
//            String query = "select m.team from Member m";

            // 컬렉션 값 연관 경로
            // t.members 이상은 탐색이 불가능하다(size 정도만 가능).
//            String query = "select t.members from Team t";

            // t.members
            // 이렇게 사용하는 경우는 없다.
//            List<Collection> result = em.createQuery(query, Collection.class).getResultList();

            // t.members.size
//            Integer result = em.createQuery(query, Integer.class).getSingleResult();

            // 컬렉션 값 연관 경로 명시적 조인(별칭을 얻으면 더 탐색하는 것이 가능해짐)
            // 묵시적 조인은 실무에서 절대 사용하지 말자!(쿼리 예측 어려움, 튜닝 어려움)
            String query = "select m.username from Team t join t.members m";
            List<Member> result = em.createQuery(query, Member.class).getResultList();

            System.out.println("result = " + result);

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
