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

            // 페치 조인

            // (1) 다대일 페치 조인
//            String query = "select m from Member m left join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member : result) {
//                if (member.getTeam() != null) {
//                    System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
//                } else {
//                    System.out.println("member = " + member.getUsername());
//                }
//                // 1) 페치 조인를 사용하지 않은 경우,
//                // 회원1, 팀A(SQL)
//                // 회원2, 팀A(1차 캐시)
//                // 회원3, 팀B(SQL)
//                // 회원 100명 -> 최악의 경우에 쿼리 1 + 100 번 날림.
//
//                // 2) 페치 조인을 사용하면 쿼리 한 방에 처리할 수 있다!
//                // 지연 로딩으로 설정을 해놓아도 페치 조인이 우선적으로 실행된다.
//            }

            // (2) 일대다 페치 조인
            // 일대다 관계에서 조인을 하는 경우 데이터가 중복(뻥튀기)될 수 있다.
            // 이 경우 distinct를 사용해서 중복을 제거할 수 있다.
//            String query = "select distinct t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | members.size = " + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member.getUsername());
//                }
//            }

            // (3) 컬렉션 페치 조인 페이징(사용하면 안됨!)
//            String query = "select distinct t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | members.size = " + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member.getUsername());
//                }
//            }

            // (4) 컬렉션 페치 조인 페이징 해결 방안
            // 1) 일대다 관계를 다대일 관계로 뒤집어서 페이징하기
//            String query = "select distinct m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getUsername() + " | member.team.name = " + member.getTeam().getName());
//            }

            // 2) 페치 조인을 사용하지 않고 페이징 처리하기(지연로딩) -> 성능이 저하될 수 있음(1 + N 문제)
//            String query = "select distinct t from Team t";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(2)
//                    .getResultList();
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | members.size = " + team.getMembers().size());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member.getUsername());
//                }
//            }
            
            // 3) @BatchSize를 사용하여 페이징 처리하기(1 + N 문제의 성능 문제 해결)
            // @BatchSize의 속성 size에 준 숫자만큼 sql에 in 절에 TEAM_ID를 추가해서 연관된 엔티티를 조회한다.
            // hibernate.jdbc.default_batch_fetch_size 속성으로 글로벌 설정이 가능하다.
            // ex) @BatchSize(size = 100)이고, TEAM_ID가 총 150개라면 먼저 100개 팀의 회원들을 조회하고, 그 다음에 나머지 50개 팀의 회원들을 조회한다.
            String query = "select distinct t from Team t";
            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            for (Team team : result) {
                System.out.println("team = " + team.getName() + " | members.size = " + team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("-> member = " + member.getUsername());
                }
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
