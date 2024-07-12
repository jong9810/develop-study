package hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        resp.getWriter().println("hello servlet!");
    }
}

// * WAR 배포 방식의 단점
// 1) 톰캣 같은 WAS를 별도로 설치해야 한다.
// 2) 개발 환경 설정이 복잡하다.
// 단순한 자바라면 별도의 설정을 고민하지 않고, min() 메서드만 실행하면 된다.
// 웹 어플리케이션은 WAS를 실행하고 또 WAR와 연동하기 위한 복잡한 설정이 필요하다.
// 3) 배포 과정이 복잡하다. WAR를 만들고 이것을 또 WAS에 전달해서 배포해야 한다.
// 4) 톰캣의 버전을 변경하려면 톰캣을 다시 설치해야 한다.

// 톰캣도 자바로 만들어져 있으니 톰캣을 마치 하나의 라이브러리처럼 포함해서 사용하는 방법
// -> 내장 톰캣(embed tomcat) 기능