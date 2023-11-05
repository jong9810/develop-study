package study.datajpa.entity;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class BaseTimeEntity {

    @CreatedDate // 생성일
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate // 마지막 수정일
    private LocalDateTime lastModifiedDate;

}
