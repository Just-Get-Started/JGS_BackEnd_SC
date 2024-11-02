package com.example.eurekaserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**")) // /eureka/**에 대해 CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/eureka/**").permitAll() // /eureka/**에 대해 인증 없이 허용
                        .anyRequest().authenticated() // 다른 모든 요청에 대해서는 인증 요구
                )
                .httpBasic(Customizer.withDefaults()); // 기본 인증 활성화

        return http.build();
    }
}
