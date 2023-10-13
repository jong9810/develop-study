package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "username")
    private String username;

    // 읽기 전용으로 일대다 양방향을 구현할 수도 있다.
//    @ManyToOne//(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id", insertable = false, updatable = false)
//    private Team team;

    // 연관관계 편의 메서드
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
