package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated());

        // csrf disable
        http
                .csrf((auth) -> auth.disable());

        // 커스텀 로그인 설정
        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll());

        return http.build();
    }
}
