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
        return builder.routes()
                .route(r -> r.path("/oauth2/**", "/login/oauth2/code/**", "/logout/**", "/api/member/**", "/member/**", "/reissue")
                        .uri("lb://MEMBER"))
                .route(r -> r.path("/team/**", "/api/team/**")
                        .uri("lb://TEAM"))
                .route(r -> r.path("/team-member/**", "/api/team-member/**")
                        .uri("lb://TEAMMEMBER"))
                .route(r -> r.path("/team-review/**", "/api/team-review/**")
                        .uri("lb://TEAMREVIEW"))
                .route(r -> r.path("/match/**", "/api/match/**")
                        .uri("lb://MATCH"))
                .route(r -> r.path("/conference/**", "/api/conference/**")
                        .uri("lb://CONFERENCE"))
                .route(r -> r.path("/image/**", "/api/image/**", "/community/**", "/api/community/**")
                        .uri("lb://COMMUNITY"))
                .route(r -> r.path("/fcm/**", "/api/fcm/**")
                        .uri("lb://FCM"))
                .route(r -> r.path("/api/sse/**")
                        .uri("lb://SSE"))
                .route(r -> r.path("/api/notification/**", "/notification")
                        .uri("lb://NOTIFICATION"))
                .build();
    }
}
