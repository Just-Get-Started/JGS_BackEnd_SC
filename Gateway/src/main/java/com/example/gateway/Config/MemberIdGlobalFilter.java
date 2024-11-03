package com.example.gateway.Config;

import com.example.gateway.Config.JWT.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberIdGlobalFilter implements GlobalFilter {

    private final JWTUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // /api/** 경로에만 필터를 적용
        if (path.startsWith("/api/")) {
            String accessToken = exchange.getRequest().getHeaders().getFirst("Access_Token");

            // 토큰이 없거나 만료된 경우
            if (accessToken == null || jwtUtil.isExpired(accessToken)) {

                log.info("Token is Null OR Expired");
                return unauthorizedResponse(exchange);
            }

            // 토큰 타입이 일치하지 않는 경우
            if (!jwtUtil.getTokenType(accessToken).equals("Access_Token")) {
                log.info("Access Token이 아님");
                return unauthorizedResponse(exchange);
            }

            // memberId를 추출하여 헤더에 추가
            Long memberId = jwtUtil.getMemberId(accessToken);
            exchange.getRequest().mutate()
                    .header("member_id", String.valueOf(memberId))
                    .build();
        }

        return chain.filter(exchange);
    }

    // 인증 실패 Response
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}