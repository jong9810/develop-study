package study.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass // 현재 클래스의 속성만을 상속받은 엔티티에 전달해서 컬럼으로 만들어준다.
@Getter
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    // JPA 주요 이벤트 어노테이션(@PrePersist, @PostPersist, @PreUpdate, @PostUpdate 등)
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        lastModifiedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }

}
