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
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setAge(10);
            member2.setType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);
            em.persist(member2);

            em.flush();
            em.clear();

            // CONCAT : 문자열 이어붙이기
//            String query = "select concat('a', 'b') from Member m";
//            String query = "select 'a' || 'b' from Member m"; // ||를 사용해서 단어를 이어줄 수도 있음.

            // SUBSTRING : 문자열 자르기
//            String query = "select substring(m.username, 2, 3) from Member m";

            // TRIM : 문자열 좌우 공백 제거
            // LOWER, UPPER : 문자열을 각각 소문자, 대문자로 변환
            // LENGTH : 문자열 길이를 출력

            // LOCATE : 'abcdefg' 문자열에서 'de'가 있는 위치(1부터 시작)값을 Integer.class로 반환
//            String query = "select locate('de', 'abcdefg') from Member m";

            // ABS, SQRT, MOD : 각각 절대값, 제곱근, 나머지를 계산해서 반환

            // SIZE : 컬렉션의 원소 개수를 반환
//            String query = "select size(t.members) From Team t";

            // INDEX(JPA 용도) : 값 타입 컬렉션에서 @OrderColumn을 사용할 경우 컬렉션에서 원소의 인덱스를 반환(잘 안 쓰임)
            // 값 타입 컬렉션의 원소가 빠지면 데이터가 Null로 들어오는 문제가 있어서 사용하지 않는 것이 좋음.
//            String query = "select size(t.members) From Team t";

            // 사용자 정의 함수
//            String query = "select function('group_concat', m.username) from Member m"; // 표준 문법
            String query = "select group_concat(m.username) from Member m"; // 이렇게 사용해도 됨(hibernate 사용하면).

            List<String> result = em.createQuery(query, String.class).getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
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
