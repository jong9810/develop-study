package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

// @Repository 어노테이션 생략 가능
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);

}
