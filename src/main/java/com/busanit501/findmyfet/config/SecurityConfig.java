// src/main/java/com/busanit501/findmyfet/config/SecurityConfig.java
package com.busanit501.findmyfet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 개발 중엔 /api/** CSRF 꺼두는 게 편해
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                // MVC의 CORS 설정 or 아래 CorsConfigurationSource 빈을 사용하도록 켜주기
                .cors(Customizer.withDefaults())
                // 프리플라이트(OPTIONS) 및 /api/** 전부 허용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                // 기본 인증/폼 로그인/로그아웃 다 끔 (401 + WWW-Authenticate 방지)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
