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

            em.flush();
            em.clear();

            // 엔티티 프로젝션을 하면 결과 엔티티를 영속성 컨텍스트에서 관리한다.
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            // 값이 바뀌었기 때문에 영속성 컨텍스트가 엔티티를 관리한다는 것을 알 수 있다.
            Member findMember = result.get(0);
            findMember.setAge(20);

            // 조인 값 조회
            //List<Team> result2 = em.createQuery("select m.team from Member m", Team.class)
            //        .getResultList();

            // 위 방식 보다는 아래 방식처럼 조인을 명시해주는 것이 좋다(조인이 일어난다는 것을 알 수 있음).
            List<Team> result3 = em.createQuery("select t from Member m join m.team t", Team.class)
                    .getResultList();

            // 임베디드 타입 프로젝션
            List<Address> result4 = em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();

            // 스칼라 타입 프로젝션
            // 1) Query 타입으로 조회
            List result5 = em.createQuery("select distinct m.username, m.age from Member m")
                    .getResultList();

            Object o = result5.get(0);
            Object[] result6 = (Object[]) o;
            System.out.println("result6[0] = " + result6[0]); // username
            System.out.println("result6[1] = " + result6[1]); // age

            // 2) Object[] 타입으로 조회
            List<Object[]> result7 = em.createQuery("select distinct m.username, m.age from Member m", Object[].class)
                    .getResultList();

            Object[] result8 = result7.get(0);
            System.out.println("result8[0] = " + result8[0]); // username
            System.out.println("result8[1] = " + result8[1]); // age

            // 3) DTO로 조회(new 키워드 사용)
            List<MemberDTO> result9 = em.createQuery("select distinct new org.example.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = result9.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

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
