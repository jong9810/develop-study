package study.querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// Q파일은 git으로 관리하면 안된다(버전 업그레이드 등 파일 수정에 의해 commit이 될 수 있음, build 폴더에 생성하면 자동으로 gitignore됨).
public class Hello {

    @Id
    @GeneratedValue
    private Long id;

}
