// src/main/java/com/busanit501/findmyfet/config/CorsConfig.java
package com.busanit501.findmyfet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();

        // 프런트 오리진 정확히 지정 (개발용)
        cfg.setAllowedOrigins(List.of("http://localhost:5173"));
        cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        // JSON 전송 시 'content-type' 요청 헤더가 들어가므로 허용 필요. *면 다 허용.
        cfg.setAllowedHeaders(List.of("*"));

        // 쿠키/세션 안 쓰면 false가 편함. (쓰면 true + AllowedOrigins에 * 금지)
        cfg.setAllowCredentials(false);

        // 필요한 경우 프리플라이트 캐시 시간
        cfg.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}
