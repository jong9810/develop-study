package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // * 빈 등록 초기화, 소멸 메서드 정의(@Bean 사용)
        // 장점
        // 1) 메서드 이름을 자유롭게 정할 수 있다.
        // 2) 스프링 빈이 스프링 코드에 의존하지 않는다(인터페이스를 사용하면 스프링에 의존함).
        // 3) 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다!!
        // destroyMethod 속성의 기본값은 "(inferred)"으로 추론이라는 뜻이다.
        // 라이브러리는 대부분 "close", "shutdown" 이라는 이름의 종료 메서드를 사용하는데,
        // destroyMethod의 추론 기능은 "close", "shutdown"이라는 메서드를 자동으로 종료 메서드로 호출해준다.
        // 추론 기능을 사용하고 싶지 않으면 destroyMethod = "" 처럼 공백을 지정하면 된다.
        @Bean/*(initMethod = "init", destroyMethod = "close")*/
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
