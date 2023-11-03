package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

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

}
