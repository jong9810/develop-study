package com.example.springjwt.config;

import com.example.springjwt.jwt.JwtFilter;
import com.example.springjwt.jwt.JwtUtil;
import com.example.springjwt.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // AuthenticationManager가 인자로 받을 AuthenticationConfiguration  객체를 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // cors 설정
        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                                CorsConfiguration configuration = new CorsConfiguration();

                                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용할 포트(프론트엔드 서버 등)
                                configuration.setAllowedMethods(Collections.singletonList("*")); // 허용할 http 메소드
                                configuration.setAllowCredentials(true); //credential 설정
                                configuration.setAllowedHeaders(Collections.singletonList("*")); // 허용할 헤더
                                configuration.setMaxAge(3600L); // 허용을 물고있을 시간

                                configuration.setExposedHeaders(Collections.singletonList("Authorization")); // 외부에 노출을 허용할 헤더
                                return configuration;
                            }
                        }));

        // csrf disable
        // 세션 방식에서는 세션이 고정되기 때문에 csrf 공격에 취약하지만,
        // JWT 방식은 세션을 stateless 상태로 관리하기 때문에 csrf 공격을 방어하지 않아도 됨.
        http
                .csrf((auth) -> auth.disable());

        // form 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        // http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        // 경로별 인가 작업
        // 어떤 경로의 요청이 들어왔는지에 따라 인증을 할지 그냥 허용할지 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        // 커스텀한 로그인 필터를 등록
        // LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
        http
                .addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 세션 설정
        // 세션을 stateless 상태로 생성하도록 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}

// JWT의 궁극적인 목표?
// https://www.youtube.com/watch?v=qIG-GNorXG4&list=PLJkjrxxiBSFCcOjy0AAVGNtIa08VLk1EJ&index=14
// https://substantial-park-a17.notion.site/14-JWT-3721466022d24a2fad0e7272e5b15c76?pvs=4
// JWT 방식과 세션 방식(Redis)를 같이 사용해서 JWT의 장점과 세션 방식의 장점을 모두 사용하는 것이 좋다.
// 하나의 방식만 고집하게 되면 결국 목적을 잃고 '만드는 사람'이 아니라 '만드는 동물'이 되어 버릴 수도 있다.
