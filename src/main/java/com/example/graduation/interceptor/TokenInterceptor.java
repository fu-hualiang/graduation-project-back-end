package com.example.graduation.interceptor;

import com.example.graduation.exception.MyException;
import com.example.graduation.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws MyException {
        System.out.println("拦截请求信息 (" + request.getMethod() + ") " + request.getRequestURI());
        String token = request.getHeader("Authorization");
        try {
            TokenUtils.validateToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "请登录");
        }
        return true;
    }
}
