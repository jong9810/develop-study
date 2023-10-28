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
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member m");

            // 결과 조회 API
            List<Member> resultList = query1.getResultList();
            Member singleResult = query1.getSingleResult();

            // 파라미터 바인딩
            List<Member> result1 = em.createQuery("select m from Member m where username =: username", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();

            // 위치 기반 바인딩은 사용하지 말자.
            List<Member> result2 = em.createQuery("select m from Member m where username =?1", Member.class)
                    .setParameter(1, "member1")
                    .getResultList();


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
