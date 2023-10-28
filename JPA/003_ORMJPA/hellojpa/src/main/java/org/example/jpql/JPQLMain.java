package org.example.jpql;


import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
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
            member.setUsername("teamA");
            member.setAge(10);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // 1) 내부 조인
//            String query = "select m from Member m join m.team t where t.name =: teamName";

            // 2) 외부 조인(left, right)
//            String query = "select m from Member m left join m.team t where t.name =: teamName";

            // 3) 세타 조인(막 조인, cross join)
//            String query = "select m from Member m, Team t where m.username = t.name";

            // 4-1) 조인 ON 절(필터링)
//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";

            // 4-2) 조인 ON 절(연관관계 없는 엔티티 외부 조인)
            String query = "select m from Member m left join Team t on m.username = t.name";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println("result.size() = " + result.size());

            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }

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
