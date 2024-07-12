package hello.embed;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EmbedTomcatSpringMain {

    public static void main(String[] args) throws LifecycleException, IOException {
        System.out.println("EmbedTomcatSpringMain.main");

        // 톰캣 설정
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 스프링 MVC의 디스패처 서블릿 생성, 스프링 컨테이너와 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        String docBase = Files.createTempDirectory("tomcat-basedir").toString();

        // 디스패처 서블릿 등록
        Context context = tomcat.addContext("", docBase);
        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
    }
}

// JAR 파일은 내부에 라이브러리 역할을 하는 JAR 파일을 포함할 수 없다. 포함한다고 해도 인식이 안된다.

// * 대안 : FatJar
// JAR 안에 JAR는 포함할 수 없지만 클래스는 얼마든지 포함할 수 있다.
// 라이브러리에 사용되는 JAR를 풀어서 나오는 클래스들을 뽑아서 새로 만드는 JAR에 포함시키는 방법.

// 하나의 JAR 파일에 필요한 라이브러리들을 내장할 수 있다.
// -> 배포, 웹 서버 설치, 실행까지 모든 것을 단순화 할 수 있음.

// * FatJar 단점
// 1) 어떤 라이브러리가 포함되어 있는지 확인하기 어렵다.
// 모두 class로 풀려있으니 어떤 라이브러리가 사용되고 있는지 추적하기 어렵다.
// 2) 파일명 중복을 해결할 수 없다.
// FatJar를 만들 때 파일명이 같은 경우 하나의 파일만 선택되어 정상적으로 작동하지 않는다.