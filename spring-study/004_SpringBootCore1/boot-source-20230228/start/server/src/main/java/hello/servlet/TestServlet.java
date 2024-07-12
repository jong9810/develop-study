package hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * http://localhost:8080/test
 */
// * JAR(Java ARchive)
// 자바는 여러 클래스와 리소스를 묶어서 JAR라고 하는 압축 파일을 만들 수 있다.
// 이 파일은 JVM 위에서 직접 실행되거나 다른 곳에서 사용하는 라이브러리로 제공된다.
// 직접 실행하는 경우, main() 메서드가 필요하고, MANIFEST.MF 파일에 실행할 메인 메서드가 있는 클래스를 지정해두어야 한다.

// * WAR(Web Application aRchive)
// WAR 파일은 웹 어플리케이션 서버에 배포할 때 사용하는 파일이다.
// WAR 파일은 WAS(Web Application Server) 위에서 실행된다.
// 참고! WAS는 JVM 위에서 실행된다.
// HTML 같은 정적 리소스와 클래스 파일을 모두 포함하기 때문에 JAR 보다 구조가 더 복잡하다.

/* WAR 구조(지켜야 함)
* WEB-INF 폴더 하위는 자바 클래스, 라이브러리, 설정 정보가 들어가는 곳이다.
* WEB-INF 외부에는 HTML, CSS 같은 정적 리소스가 사용되는 영역이다.
WEB-INF
    classes : 실행 클래스 모음
    lib : 라이브러리 모음
    web.xml : 웹 서버 배치 설정 파일(생략 가능)
incex.html : 정적 리소스
*/
@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.service");
        resp.getWriter().println("test");
    }
}
