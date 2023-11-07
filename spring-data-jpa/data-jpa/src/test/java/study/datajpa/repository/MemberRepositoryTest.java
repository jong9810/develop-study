package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @PersistenceContext EntityManager em;

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;

    // @Autowired보다 생성자 injection이 더 낫다.
    @Autowired MemberQueryRepository memberQueryRepository;

    @Test
    public void testMember() {
        // 스프링 데이터 JPA가 구현 클래스를 만들어서 주입해준다(프록시 객체 사용).
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass());
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 값 변경하기 검증
        findMember1.setUsername("member!!!!!!");

        // 리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findHelloBy() {
        List<Member> helloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    public void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);

        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findMember("AAA", 10);
        Member findMember = result.get(0);

        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testFindUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();

        for (String username : usernameList) {
            System.out.println("username = " + username);
        }

        assertThat(usernameList.get(0)).isEqualTo(m1.getUsername());
        assertThat(usernameList.get(1)).isEqualTo(m2.getUsername());
    }

    @Test
    public void testFindMemberDto() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();

        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void testFindByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> names = Arrays.asList("AAA", "BBB");

        List<Member> result = memberRepository.findByNames(names);

        for (Member member : result) {
            System.out.println("member = " + member);
        }

        assertThat(result.get(0)).isEqualTo(m1);
        assertThat(result.get(1)).isEqualTo(m2);
    }

    @Test
    public void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> list = memberRepository.findListByUsername("AAA");
        Member member = memberRepository.findMemberByUsername("AAA");
        Optional<Member> optional = memberRepository.findOptionalByUsername("AAA");

        System.out.println("list = " + list.get(0));
        System.out.println("member = " + member);
        System.out.println("optional = " + optional.get());

        // 만약 컬렉션으로 조회했을 때 결과가 없으면 null을 반환하지 않고 비어있는 컬렉션을 반환한다.
        // 컬렉션으로 조회하면 절대 null이 아니다!!
        List<Member> result1 = memberRepository.findListByUsername("sdjklnfgs");
        System.out.println("result1.size() = " + result1.size());

        // 단건 조회를 했을 때 결과가 없으면 null을 반환한다(스프링 데이터 JPA에서).
        // 순수 JPA에서는 .getSingleResult()를 했을 때 결과가 없으면 NoResultException을 터뜨린다.
        Member result2 = memberRepository.findMemberByUsername("sdlfkjs");
        System.out.println("result2 = " + result2);

        // 데이터가 있을지 없을지 모를 떄는 그냥 Optional을 쓰면 된다.
        Optional<Member> result3 = memberRepository.findOptionalByUsername("sdlfkjs");
        System.out.println("result3 = " + result3);

        // 단건 조회를 했을 때 결과가 두 건 이상이면 Optional이든 아니든 예외가 터진다((javax.persistence.)NonUniqueResultException -> (org.springframework.dao.)IncorrectResultSizeDataAccessException).
        Member result4 = memberRepository.findMemberByUsername("AAA");
        Optional<Member> result5 = memberRepository.findOptionalByUsername("AAA");
        System.out.println("result4 = " + result4);
        System.out.println("result5 = " + result5);
    }

    @Test
    public void paging() {
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        // 참고 : 스프링 데이터 JPA에서 페이지는 0번 부터 시작한다.
        int age = 10;
        // PageRequest는 Pageable 인터페이스의 구현체이고 가장 많이 쓰인다.
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        // when
        // 1) Page 객체로 반환
//        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        
        // 2) Slice 객체로 반환
//        Slice<Member> page = memberRepository.findByAge(age, pageRequest);

        // 3) 엔티티 컬렉션 객체로 반환
//        List<Member> page = memberRepository.findByAge(age, pageRequest);
//        for (Member member : page) {
//            System.out.println("member = " + member);
//        }

        // 4) DTO 컬렉션 객체로 반환
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), m.getTeam().getName()));

        // then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();

        for (Member member : content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(2);
    }

    @Test
    void bulkUpdate() {
        // Given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));
        memberRepository.save(new Member("member6", 90));

        // When
        int resultCount = memberRepository.bulkAgePlus(20);

        // 벌크성 수정 쿼리는 1) 영속성 컨텍스트가 빈 상태에서 수행하거나
        // 2) 벌크성 수정 쿼리 수행 후 영속성 컨텍스트를 초기화해주어야 한다.
        //em.flush(); // JPA가 JPQL을 수행하기 전에 flush는 자동으로 날려준다.
        //em.clear(); // @Modifying 어노테이션에서 clearAutomatically = true를 주면 생략 가능하다.

        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5);

        // Then
        assertThat(resultCount).isEqualTo(4);
    }

    @Test
    void findMemberLazy() {
        // Given
        // member1 -> teamA
        // member2 -> teamB
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member1", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        // When
//        List<Member> members = memberRepository.findAll();
//        List<Member> members = memberRepository.findMemberFetchJoin();
//        List<Member> members = memberRepository.findEntityGraphByUsername("member1");
        List<Member> members = memberRepository.findNamedEntityQueryByUsername("member1");

        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team.class(before) = " + member.getTeam().getClass());
            System.out.println("member.team.name = " + member.getTeam().getName());
            System.out.println("member.team.class(after) = " + member.getTeam().getClass());
        }
    }
    
    @Test
    void queryHint() {
        // Given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        // When
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush();
    }
    
    @Test
    void lock() {
        // Given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        // When
        List<Member> findMember = memberRepository.findLockByUsername("member1");
    }

    // 사용자 정의 레포지토리 구현
    @Test
    void callCustom() {
        List<Member> result = memberRepository.findMemberCustom();
    }

    @Test
    void callMemberQueryRepository() {
        List<Member> result = memberQueryRepository.findAllMembers();
    }

    // Specifications Test
    @Test
    void specBasic() {
        // Given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        // When
        Specification<Member> spec = MemberSpec.username("m1").and(MemberSpec.teamName("teamA"));
        List<Member> result = memberRepository.findAll(spec);

        // Then
        assertThat(result.size()).isEqualTo(1);
    }

    // QueryByExample Test(실무에서 사용하기에는 애매함)
    // 장점
    //   1) 동적 쿼리를 편리하게 처리할 수 있다.
    //   2) 도메인 객체를 그대로 사용한다.
    //   3) 데이터저장소를 RDB에서 NOSQL로 변경해도 코드 변경이 없게 추상화 되어 있다.
    //   4) 스프링 데이터 JPA의 JpaRepository 인터페이스에 포함되어 있다.
    // 단점
    //   1) 내부 조인은 가능하지만 외부 조인은 불가능하다(외부 조인이 하나라도 생기면 사용하지 못함).
    //   2) 다음 예시와 같은 중첩 제약조건은 안된다.
    //      firstName = ?0 or (firstName = ?1 and lastname = ?2)
    //   3) 매칭 조건이 매우 단순하다.
    //        문자는 starts/contains/ends/regexp 정도만 가능하다.
    //        다른 속성은 정확한 매칭(=)만 가능하다.
    @Test
    public void queryByExample() {
        // Given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        // When
        // Probe 생성
        Member member = new Member("m1");
        Team team = new Team("teamA");
        member.setTeam(team);

        // age 속성은 검색 조건에서 제외(null은 기본적으로 무시하지만 primitive 타입은 무시하지 않음(값이 초기화되기 때문))
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("age");

        // Probe와 ExampleMatcher를 가지고 쿼리를 생성할 때 필요한 Example 객체를 생성한다.
        Example<Member> example = Example.of(member, matcher);

        List<Member> result = memberRepository.findAll(example);

        // Then
        assertThat(result.get(0).getUsername()).isEqualTo("m1");
    }

    // Projections(실무의 복잡한 쿼리를 해결하기에는 한계가 있음)
    // 조인이 필요한 경우에는 최적화가 루트 엔티티에만 적용되므로 사용하기 조금 애매해진다(root 엔티티만 조회할 경우에 유용함).
    // 프로젝션 대상이 root 엔티티면, JPQL select 절이 최적화(필드만 조회)가 된다.
    // 프로젝션 대상이 root 엔티티가 아니면, left join으로 처리하고 모든 필드를 select해서 엔티티로 조회한 다음 계산해서 필요한 필드만 가져온다.
    @Test
    void  projections() {
        // Given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        // When
        //List<UsernameOnlyDto> result = memberRepository.findProjectionsByUsername("m1");
        List<NestedClosedProjections> result = memberRepository.findProjectionsByUsername("m1", NestedClosedProjections.class);

        // Then
        for (NestedClosedProjections nestedClosedProjections : result) {
            String username = nestedClosedProjections.getUsername();
            String teamName = nestedClosedProjections.getTeam().getName();
            System.out.println("username = " + username);
            System.out.println("teamName = " + teamName);
        }
    }

    // Native Query(가급적이면 사용하지 않는 게 좋음, 어쩔 수 없을 때만 사용)
    // * Native Query보다는 Custom Repository를 만들어서 JdbcTemplate이나 MyBatis를 사용하는 것이 더 낫다.
    // * 반환 타입
    //   1) Object[] (안씀)
    //   2) Tuple (안씀)
    //   3) DTO(스프링 데이터 인터페이스 Projections 지원)
    // * 제약
    //   1) Sort 파라미터를 통한 정렬이 정상 동작하지 않을 수 있다(믿지 말고 직접 처리하자).
    //   2) JPQL처럼 애플리케이션 로딩 시점에 문법 확인이 불가능하다.
    //   3) 동적 쿼리를 사용하지 못한다.
    // * Projections 활용
    // 정적 쿼리인 경우에는 Projections를 Native Query와 함께 사용하는 것도 좋다.
    // 동적 쿼리는 JdbcTemplate이나 MyBatis, jooq 등을 사용해야 한다.
    @Test
    void nativeQuery() {
        // Given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();
                
        // When
//        Member result = memberRepository.findByNativeQuery("m1");
        Page<MemberProjection> result = memberRepository.findByNativeProjection(PageRequest.of(0, 10));

        // Then
        List<MemberProjection> content = result.getContent();
        for (MemberProjection memberProjection : content) {
            System.out.println("memberProjection.username = " + memberProjection.getUsername());
            System.out.println("memberProjection.teamName = " + memberProjection.getTeamName());
        }
    }

}