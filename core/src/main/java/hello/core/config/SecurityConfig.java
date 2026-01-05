package hello.core.config;

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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API 테스트 시에는 CSRF를 꺼야 포스트맨/스웨거가 작동함
                .headers(headers -> headers.frameOptions(f -> f.disable())) // H2 콘솔 화면을 보기 위해 필요
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 모든 요청을 로그인 없이 허용 (과제 조건)
                )
                .formLogin(form -> form.disable()); // 문제의 그 로그인 화면을 비활성화!

        return http.build();
    }
}