package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV3SpringMVC implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3SpringMVC.onStartup");

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너에 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        // 디스패처 서블릿을 서블릿 컨테이너에 등록(이름 겹치지 않게 주의!)
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV3", dispatcher);

        // 모든 요청이 디스패처 서블릿을 통하도록 설정
        servlet.addMapping("/");
    }
}

// 스프링 MVC는 서블릿 컨테이너 초기화 작업을 이미 만들어 두었다.
// 덕분에 개발자는 애플리케이션 초기화 코드만 작성하면 된다.
// 스프링이 지원하는 애플리케이션 초기화를 사용하려면 WebApplicationInitializer 인터페이스를 구현하면 된다.

// SpringServletContainerInitializer 클래스가 WebApplicationInitializer 인터페이스를 구현한
// 클래스(AppInitV3SpringMVC)의 서블릿 컨테이너 초기화를 대신 수행한다.
// 스프링 MVC를 사용하면 개발자는 WebApplicationInitializer를 구현하는 AppInitV3SpringMVC 클래스만 구현하면 된다.
