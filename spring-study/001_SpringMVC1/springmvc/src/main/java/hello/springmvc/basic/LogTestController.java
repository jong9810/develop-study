package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // @Slf4j 어노테이션이 대신 선언해준다.
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // 무조건 출력됨
        System.out.println("name = " + name);

        // 이렇게 사용하면 안됨(올바른 로그 사용법은 아래 {} 사용하는 것)
        // 문자열 + 연산이 일어나는데 출력이 안 되는 경우에는 리소스가 낭비됨
        log.trace("trace log={}" + name);
        
        // 로그의 상태에 따라 다른 메소드 사용(로그 레벨에 따라 출력되기도 하고 안 되기도 함)
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
