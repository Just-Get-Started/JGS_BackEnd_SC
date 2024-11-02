package com.example.gateway.Config;

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

        /**
         * uri에는 Eureka에 등록되어있는 Application name으로 설정
         * lb -> Load Balancing
         */
        return builder.routes()
                .route(r -> r.path("/member/**")
                        .uri("lb://MEMBER"))
                .build();
    }
}
