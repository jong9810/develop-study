package study.datajpa.repository;

// 중첩 구조의 닫힌 Projection : 첫 번째(Root)는 최적화(필드만 조회)가 되지만 두 번째부터는 최적화가 되지 않는다(조인은 left join을 함).
public interface NestedClosedProjections {

    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
