package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드 간에 공유하면 절대 안된다.
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 내에서 실행해야 한다(수정, 삭제, 삽입).
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //Member findMember = em.find(Member.class, 1L); // 조회
            //System.out.println("findMember.id = " + findMember.getId());
            //System.out.println("findMember.name = " + findMember.getName());
            //findMember.setName("HelloJPA"); // 수정
            //em.remove(findMember); // 삭제

            // JPQL : SQL을 추상화한 객체 지향 쿼리 언어.
            // 엔티티 객체를 대상으로 쿼리를 작성한다.
            // db 방언에 맞춰서 JPA가 번역을 해준다.
            // SQL 문법과 유사하다(select, from, where, group by, having, join 지원).
            List<Member> result = em.createQuery("select m from Member as m", Member.class) // Member 엔티티를 선택해옴.
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.id = " + member.getId());
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
