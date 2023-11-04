package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

// @Repository 어노테이션 생략 가능
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    @Query(name = "Member.findByUsername") // 생략해도 네임드 쿼리를 호출해준다.
    // 먼저 Member.findByUsername이라는 네임드 쿼리가 있는지 확인하고 있으면 실행한다.
    // 네임드 쿼리가 없으면 메서드 이름으로 생성되는 쿼리를 실행한다.
    // 네임드 쿼리는 실무에서 거의 사용되지 않는다.
    // 스프링 데이터 JPA가 레포지토리에 쿼리를 직접 작성할 수 있는 기능을 제공하는데 그 기능을 사용한다.
    List<Member> findByUsername(@Param("username") String username);

    // @Query로 엔티티 조회하기
    @Query("select m from Member m where m.username = :username and m.age = :age")
    // 애플리케이션 로딩 시점에 오류를 잡아준다.
    // @Query에 작성한 쿼리는 이름이 없는 네임드 쿼리라고 생각하면 된다.
    // 실무에서 간단한 쿼리는 메서드 이름으로 쿼리를 생성하고, 복잡한 쿼리는 @Query에 직접 쿼리를 작성하면 된다(정적 쿼리).
    // 참고 : 동적 쿼리는 QueryDsl을 사용하는 것이 유집보수성을 높이는 방법이다.
    List<Member> findMember(@Param("username") String username, @Param("age") int age);

    // @Query로 값 조회하기
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // @Query로 DTO 조회하기
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 컬렉션 파라미터 바인딩
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    // 반환 타입
    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional

    // 페이징과 정렬
//    Page<Member> findByAge(int age, Pageable pageable); // Page : totalCount 쿼리를 같이 날린다.
//    Slice<Member> findByAge(int age, Pageable pageable); // Slice : 데이터를 limit 보다 한 개 더 조회한다(다음 페이지 있는지 없는지 확인, totalCount 쿼리 안 날림).
//    List<Member> findByAge(int age, Pageable pageable); // 그냥 리스트(컬렉션)로 반환받을 수도 있다.
    // 페이징과 정렬 - countQuery 분리하기(count 쿼리에서 성능 최적화가 필요할 때)
    @Query(
            value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m"
    )
    Page<Member> findByAge(int age, Pageable pageable);
    // 만약 sorting 조건이 너무 복잡해서 Sort.by로 할 수 없다면 @Query(value = "")에 직접 sorting 조건을 넣어주면 된다.

    // 벌크성 수정 쿼리
    // 주의점!
    // 벌크성 수정 쿼리는 영속성 컨텍스트를 무시하고 db에 수정 쿼리를 강제로 날리기 때문에 영속성 컨텍스트와 db 데이터가 맞지 않을 수 있다.
    @Modifying(clearAutomatically = true) // @Modifying 어노테이션이 있어야 .excuteUpdate() 메서드를 호출한다(없으면 InvalidDataAccessApiUsingException 터짐).
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    // @EntityGraph : 스프링 데이터 JPA에서 fetch join을 사용하는 방법.
    // 간단한 쿼리일 경우 @EntityGraph(attributePaths = {})를 사용하고 복잡한 경우에는 @Query()에 직접 작성하는 것이 편하다.
    // 1) @Query로 직접 JPQL 작성
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    // 2) 메서드명으로 쿼리 생성 + @EntityGraph
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    // 3) JPQL + @EntityGraph
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    // 4) 엔티티 객체에 @NamedEntityGraph 정의해서 사용
    @EntityGraph("Member.all")
    List<Member> findNamedEntityQueryByUsername(@Param("username") String username);

    // JPA 힌트


}
