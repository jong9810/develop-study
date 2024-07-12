package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

// * 서블릿을 등록하는 방법
// 1) @WebServlet 어노테이션 : 편리하지만 하드 코딩된 것처럼 동작한다.
// 2) 프로그래밍 방식 : 코드가 더 길지만 유연하게 변경할 수 있다.
// (외부 설정 읽어서 등록 가능, 특정 조건에 따라 분기 가능, 생성자에 필요한 정보 넘기는 것 가능)
public class AppInitV1Servlet implements AppInit {

    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartup");

        // 순수 서블릿 코드 등록
        ServletRegistration.Dynamic helloServlet =
                servletContext.addServlet("helloServlet", new HelloServlet());
        helloServlet.addMapping("/hello-servlet");
    }
}
