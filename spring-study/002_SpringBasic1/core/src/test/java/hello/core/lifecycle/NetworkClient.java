package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + ", message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect: " + url);
    }

/*
    // * InitializingBean, DisposableBean 인터페이스 사용(거의 사용하지 않음)
    // 단점
    // 1) 스프링 전용 인터페이스이기 때문에 코드가 스프링 프레임워크에 의존하게 된다.
    // 2) 초기화, 소멸 메서드의 이름을 변경할 수 없다.
    // 3) 내가 코드를 고칠 수 없는 외부 라이브러리에는 적용할 수 없다.
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
*/

    // @PostConstruct, @PreDestroy 어노테이션 사용(자바(JSR-250)에서 지원, 스프링 외에서도 사용 가능)
    // 어노테이션만 붙이면 되기 때문에 매우 편리하고, 컴포넌트 스캔과 잘 어울린다.
    // 유일한 단점은 외부 라이브러리에 적용하지 못한다는 것이다(외부 라이브러리를 초기화, 종료해야할 경우는 @Bean의 기능을 사용).
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
