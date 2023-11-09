package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Querydsl DTO 조회를 사용하려면 기본 생성자가 필수이다(public 접근 제어자).
public class MemberDto {

    private String username;
    private int age;

    @QueryProjection // compileQuerydsl을 해주면 DTO가 Q파일로 생성된다.
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
