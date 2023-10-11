package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {

    @Id // 기본키 직접 할당
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 할당
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate lastModifiedDate;
    @Lob
    private String description;

    public Member() {
    }

}
