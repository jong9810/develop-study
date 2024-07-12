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

// * 실행 가능 JAR
// 스프링 부트는 FatJar의 문제를 해결하기 위해 jar 내부에 jar를 포함할 수 있는 특별한 구조의 jar를 만들고
// 동시에 내부에 jar를 포함한 jar를 실행할 수 있게 했다. 이것을 실행 가능 jar(Excutable Jar)라고 한다.

// * 실행 가능 jar로 FatJar의 두 가지 문제를 깔끔하게 해결할 수 있다.
// 1) jar 내부에 jar를 포함하기 때문에 어떤 라이브러리가 포함되어 있는지 쉽게 확인할 수 있다.
// 2) jar 내부에 jar를 포함하기 때문에 다른 경로에 같은 이름의 파일이 있어도 둘다 인식할 수 있다.
// 참고! 실행 가능 jar는 자바 표준은 아니고, 스프링 부트에서 새롭게 정의한 것이다.

/* 실행 가능 jar 내부구조
boot-0.0.1-SNAPSHOT.jar
	META-INF
		MANIFEST.MF
	org/springframework/boot/loader
		JarLauncher.class : 스프링 부트 main()을 실행하는 클래스
	BOOT-INF
		classes : 우리가 개발한 class 파일과 리소스 파일
			hello/boot/BootApplication.class
			hello/boot/controller/HelloController.class
			...
		lib : 외부 라이브러리
			spring-webmvc-6.0.4.jar
			tomcat-embed-core-10.1.5.jar
			...
		classpath.idx : 외부 라이브러리 모음
		layers.idx : 스프링 부트 구조 정보
*/

// * JarLauncher 클래스
// JarLauncher 클래스는 스프링 부트가 빌드시에 넣어준다.
// jar 내부에 있는 jar를 읽어들이고 특별한 구조에 맞게 클래스 정보도 읽어들이는 작업을 처리한다.

// * 실행 과정 정리
// 1) java -jar xxx.jar
// 2) MANIFEST.MF 인식
// 3) JarLauncher.main() 실행
//		BOOT-INF/classes/ 인식
//		BOOT-INF/lib/ 인식
// 4) BootApplication.main() 실행