package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages : 탐색할 패키지의 시작 위치를 지정한다(하위 패키지도 탐색함).
        // basePackages나 basePackageClasses를 지정하지 않으면 @ComponentScan을 붙인 설정 정보 클래스의 패키지가 시작 위치가 된다.
//        basePackages = {"hello.core"},
//        basePackageClasses = {AutoAppConfig.class},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 주의!! 의도적인 경우보다 여러 설정들이 꼬여서 충돌이 나는 경우가 더 많다(찾아내기 굉장히 어려움)!
    // 만약 자동 등록 빈과 수동 등록 빈의 이름이 같은 경우에는, 수동 등록빈이 우선권을 가진다(Spring Framework의 코어 모듈에서).
    // SpringBoot를 통해서 Application을 실행했을 때에는 오류를 발생시킨다.
    // application.properties 파일에 spring.main.allow-bean-definition-overriding 속성으로 오류를 발생시킬지 수동 빈으로 오버라이딩할지 결정할 수 있음.
    // 코드가 깔끔해지는 대신 불명확해지는 것보다 코드가 좀 지저분하더라도 명확한 편이 좋다(협업).
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberREpository() {
        return new MemoryMemberRepository();
    }
}
