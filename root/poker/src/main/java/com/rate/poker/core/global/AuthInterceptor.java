package com.rate.poker.core.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestURI = request.getRequestURI();
        System.out.println("[interceptor] : " + requestURI);
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            // 로그인 되지 않음
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            System.out.println("[미인증 사용자 요청]");
            out.println("<script>alert('권한이 없습니다. 로그인 해주세요.');" +
                    "location.href='/index';</script>");
            out.flush();
            out.close();
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
