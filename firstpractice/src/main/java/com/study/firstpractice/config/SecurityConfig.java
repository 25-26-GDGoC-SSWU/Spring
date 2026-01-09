package com.study.firstpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Swagger 접속을 위해 추가한 코드입니다...
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 보호 비활성화 (POST 요청 허용을 위해 필수)
                .csrf(csrf -> csrf.disable())

                // 2. CORS 설정 (선택사항이나 추가 권장)
                .cors(cors -> cors.disable())

                // 3. 모든 요청에 대해 인증 없이 접근 허용
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 4. 기본 로그인 폼 및 HTTP Basic 인증 비활성화
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}