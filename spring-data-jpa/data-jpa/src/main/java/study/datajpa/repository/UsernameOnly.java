package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    // SpEL(Spring Expression Language)
    // Open Projections : 엔티티 전체를 가져와서 @Value 안에 SpEL로 정의한 속성으로 값을 만들어준다.
    // Close Projections : 정확하게 매칭이 되는 경우에는 엔티티 전체가 아니라 하나의 속성만을 SQL로 조회해온다(최적화).
//    @Value("#{target.username + ' ' + target.age}")
    String getUsername();
}
