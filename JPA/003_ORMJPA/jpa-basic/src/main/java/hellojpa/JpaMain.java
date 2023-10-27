package hellojpa;


import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // JPQL
//            List<Member> result = em.createQuery(
//                    "select m from Member m where m.username like '%kim%'", Member.class
//            ).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }

            // JPA Criteria
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            Root<Member> m = query.from(Member.class);
//
//            CriteriaQuery<Member> cq = query.select(m);
//
//            String username = "kim";
//            if (username != null) {
//                cq = cq.where(cb.equal(m.get("username"), "kim"));
//            }
//
//            List<Member> resultList = em.createQuery(cq).getResultList();

            // 네이티브 SQL
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//
//            // flush -> commit, query
//
//            List<Member> resultList = em.createNativeQuery("select  MEMBER_ID, city, street, zipcode, USERNAME from MEMBER", Member.class)
//                    .getResultList();
//
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

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
