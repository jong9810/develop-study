package study.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // 이벤트를 기반으로 동작한다는 것을 명시적으로 지정해주어야 한다.
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreatedDate // 생성일
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate // 마지막 수정일
    private LocalDateTime lastModifiedDate;

}
