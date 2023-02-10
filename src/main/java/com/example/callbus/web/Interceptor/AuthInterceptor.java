package com.example.callbus.web.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("msg", "권한이 없습니다.");
            throw new RuntimeException(errorMap.toString());
        }


    }


}
