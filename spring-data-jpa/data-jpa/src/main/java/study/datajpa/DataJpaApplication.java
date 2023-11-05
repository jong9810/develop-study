package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

// 스프링 데이터 JPA의 Auditing을 사용하려면 @EnableJpaAuditing 어노테이션을 메인 클래스에 꼭 지정해주어야 한다!
// 이 방법 말고 META-INF/orm.xml 파일을 생성해서 지정해주는 방법도 있다(추가해야할 것이 많을 때 사용).
@EnableJpaAuditing//(modifyOnCreate = false) // update되지 않은 경우에는 데이터가 삽입되지 않는다(null인 상태, 권장 안함).
@SpringBootApplication
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		// 실무에서는 SpringSecurity 등에서 세션 정보를 가져와서 아이디를 꺼내야 한다.
		return () -> Optional.of(UUID.randomUUID().toString());

		// 위 코드와 정확히 같은 코드(인터페이스에서 메서드가 하나일 경우 람다로 변환할 수 있음)
//		return new AuditorAware<String>() {
//			@Override
//			public Optional<String> getCurrentAuditor() {
//				return Optional.of(UUID.randomUUID().toString());
//			}
//		}
	}

}
