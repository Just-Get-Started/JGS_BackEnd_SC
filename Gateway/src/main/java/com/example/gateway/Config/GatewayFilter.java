package com.example.gateway.Config;

import com.example.gateway.Config.JWT.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayFilter {
    /**
     * 라우팅
     */
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/oauth2/**", "/login/oauth2/code/**", "/logout/**", "/api/member/**", "/member/**")
                        .uri("lb://MEMBER"))
                .build();
    }
}
