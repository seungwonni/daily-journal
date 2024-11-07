package com.poker.core.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
public class AuthInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestURI = request.getRequestURI();
        System.out.println("[interceptor] : " + requestURI);
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            // 로그인 되지 않음
            System.out.println("[미인증 사용자 요청]");
            //로그인으로 redirect
            response.sendRedirect("/index");
            return false;
        }
        // 로그인 되어있을 떄
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("[interceptor] postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("[interceptor] afterCompletion");
    }
}
