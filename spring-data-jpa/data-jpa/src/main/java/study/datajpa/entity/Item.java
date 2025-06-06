package study.datajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @GeneratedValue를 식별자에 사용하지 않는 경우 Persistable 인터페이스를 구현하고
// isNew() 메서드에 createdDate가 null일 때 새로운 객체라고 판단하면 된다.
public class Item implements Persistable<String> {

//    @Id
//    @GeneratedValue
//    private Long id;

    @Id
    private String id;

    @CreatedDate // JPA persist 전에 호출됨.
    private LocalDateTime createdDate;

    public Item(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
