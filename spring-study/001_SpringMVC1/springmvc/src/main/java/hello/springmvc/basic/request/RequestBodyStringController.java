package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    // HttpMessageConverter
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) {
        // HttpEntity : http 헤더, 바디 정보를 편리하게 조회할 수 있는 객체
        // 요청 파라미터를 조회하는 기능과는 아무 상관이 없다(@RequestParam, @ModelAttribute).
        // 응답에도 사용 가능하다(메시지 바디 정보 직접 반환, 헤더 정보 포함 가능, view 조회는 안함).
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.CREATED); // ResponseEntity : 상태 코드를 추가로 넣을 수 있다.
    }

    @ResponseBody
    // @RequestHeader : 헤더 정보를 조회할 수 있다.
    @PostMapping("/request-body-string-v3")
    public String requestBodyStringV3(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }

}
