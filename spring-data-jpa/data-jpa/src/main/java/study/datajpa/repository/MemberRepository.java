package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
