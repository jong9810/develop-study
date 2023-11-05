package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA가 엔티티를 관리하려면 최소한 protect 접근 제어자를 가지는 기본 생성자가 필요하다(private 안됨).
@ToString(of = {"id", "username", "age"}) // team은 양방향 연관관계이기 때문에 toString에 사용하면 무한 루프에 빠질 위험이 있다.
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team"))
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            this.changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    // setter 대신 이름을 바꾸는 메서드를 정의하는 것이 더 나음.
//    public void changeUsername(String username) {
//        this.username = username;
//    }
    
    //==연관관계 편의 메서드==//
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
