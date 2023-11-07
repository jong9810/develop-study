package study.datajpa.repository;

public class UsernameOnlyDto {

    private final String username;

    // 생성자의 파라미터명을 가지고 조회를 하기 때문에 테이블의 필드명과 같아야 한다.
    public UsernameOnlyDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
