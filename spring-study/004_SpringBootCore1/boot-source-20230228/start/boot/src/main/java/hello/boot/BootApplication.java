package hello.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

	// 아래의 코드 한 줄로 인해 수 많은 일들이 발생하지만 핵심은 2가지이다.
	// 1) 스프링 컨테이너를 생성한다,
	// 2) WAS(내장 톰캣)를 생성한다.
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}

// * 스프링 부트와 내장 WAS를 같이 사용하면 좋은 점
// 내장 톰캣을 사용하여 빌드와 배포를 편리하게 한다.
// 빌드시 하나의 JAR를 사용하면서, 동시에 FatJar 문제도 해결한다.
// 지금까지 진행한 내장 톰캣 서버를 실행하기 위한 복잡한 과정을 모두 자동으로 처리한다.
