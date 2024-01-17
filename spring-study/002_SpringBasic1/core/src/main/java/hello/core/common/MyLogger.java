package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

// * proxyMode
// 적용 대상이 클래스이면 'TARGET_CLASS', 인터페이스이면 'INTERFACES'를 선택하면 된다.
// MyLogger 가짜 프록시 객체를 만들어두고 HTTP request와 상관없이 가짜 프록시 객체를 다른 빈에 미리 주입해둘 수 있다.
// MyLogger의 기능을 호출하는 시점에 진짜 MyLogger 객체를 찾아서 주입해준다.

// * 프록시 객체
// 프록시 객체(빈)는 요청이 오면 그때 내부에서 진짜 객체(빈)를 요청하는 위임 로직이 들어있다.
// 프록시 객체는 내부에 진짜 객체의 찾는 방법을 알고 있다.
// 클라이언트가 'myLogger.logic()'을 호출하면 사실은 프록시 객체의 메서드를 호출한 것이다.
// 그러면 프록시 객체가 request 스코프의 진짜 객체의 'myLogger.logic()'을 호출한다.
// 프록시 객체는 원본 클래스를 상속받아서 만들어지기 때문에, 이 객체를 사용하는 클라이언트 입장에서는 원본인지 아닌지 모르고 동일하게 사용할 수 있다(다형성).

// * 동작 정리
// CGLIB이라는 라이브러리로 내 클래스를 상속받아서 프록시 객체를 만들어서 주입한다.
// 이 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있다.
// 프록시 객체는 실제 request scope과는 관계가 없다(싱글톤처럼 동작).

// * 특징 정리
// 프록시 객체 덕분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope 빈을 사용할 수 있다.
// 사실 Provider를 사용하든 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연 처리한다는 것이다.
// 단지 어노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다(다형성과 DI 컨테이너가 가진 큰 강점).
// 꼭 웹 스코프(request, session, application, websocket 등)가 아니어도 프록시는 사용할 수 있다.

// * 주의점
// 마치 싱글톤을 사용하는 것 같지만 다르게 동작하기 때문에 결국 주의해서 사용해야 한다.
// 이런 특별한 scope는 꼭 필요한 곳에만 최소화해서 사용하자(무분별하게 사용하면 유지보수 어려움)!
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;

    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
