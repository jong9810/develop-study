package hellojpa;


import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());
//            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass()); // Proxy 객체
//
//            System.out.println("=======================");
//            System.out.println("teamName = " + m.getTeam().getName()); // 강제 초기화
//            System.out.println("=======================");

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();
            // SQL : select * from member;
            // SQL : select * from team where team_id = xxx;


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
