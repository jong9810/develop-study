package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // Spring Security 버전마다 SecurityConfig 구현 방법이 조금 달라서 버전에 맞는 방법을 찾아보면서 작성해야 한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. 인가 작업 : 인가 동작 순서는 requestMatchers 상단부터 아래로(순서를 잘 지켜서 작성해야함!!).
        // permitAll : 모든 사용자가 로그인을 하지 않아도 접근 가능
        // hasRole : 로그인 한 후에 특정한 role이 있어야 접근 가능
        // hasAnyRole : 로그인을 한 후에 여러가지 role 중 하나 이상이 있으면 접근 가능
        // authenticated : 로그인만 하면 접근 가능
        // denyAll : 모든 사용자가 로그인을 해도 접근 불가
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated());

        // 2. csrf 설정
        // api 서버의 경우 보통 세션을 stateless 상태로 관리하기 때문에 csrf 설정을 disable해도 보안상 문제가 없다.
//        http
//                .csrf((auth) -> auth.disable());

        // 3. 커스텀 로그인 설정
        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll());

        // 4. 다중 로그인 설정
        // maximumSessions(정수) : 하나의 아이디에 대해 다중 로그인 허용 개수.
        // maxSessionsPreventsLogin(불린) : 다중 로그인 개수를 초과하였을 때 새로운 로그인 시도를 차단할 것인지.
        //                                 true; 새로운 로그인 차단, false; 기존 세션 하나 삭제하고 새로운 로그인 허용.
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        // 5. 세션 고정 공격에 대해 보호
        // none() : 로그인 시 세션 정보를 변경하지 않음.
        // newSession() : 로그인 시 세션을 새로 생성.
        // changeSessionId() : 로그인 시 동일한 세션에 대해 id를 변경.
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        return http.build();
    }

    // * InMemory 유저 정보 저장
    // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
    /*@Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("user1")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }*/

}

