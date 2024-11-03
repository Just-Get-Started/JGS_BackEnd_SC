package com.example.memberservice.OAuth2.Logout;

import com.example.memberservice.JWT.JWTUtil;
import com.example.memberservice.OAuth2.Redis.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshRepository;

    public CustomLogoutFilter(JWTUtil jwtUtil, RefreshTokenRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        //path and method verify
        System.out.println("logout1");
        String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$")) {

            filterChain.doFilter(request, response);
            return;
        }
        String requestMethod = request.getMethod();
        System.out.println("logout2");
        if (!requestMethod.equals("POST")) {

            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("logout3");
        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("Refresh_Token")) {

                refresh = cookie.getValue();
            }
        }

        System.out.println("logout4");
        //refresh null check
        if (refresh == null) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("logout5");
        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("logout6");
        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getTokenType(refresh);
        if (!category.equals("Refresh_Token")) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("logout7");
        // Redis에 저장되어 있는지 확인 없으면 에러 코드 반환
        if (!refreshRepository.findByEmail(refresh).isPresent()) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("logout8");
        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshRepository.delete(refresh);

        System.out.println("logout9");

        //Refresh 토큰 Cookie 값 0
        Cookie cookie = new Cookie("Refresh_Token", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite","None");
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        System.out.println("logout10");

        response.addCookie(cookie);
        response.setStatus(HttpStatus.OK.value());
    }
}
