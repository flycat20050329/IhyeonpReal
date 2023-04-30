package com.ykm.ihyeon.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.google.gson.Gson;

public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        super.commence(request, response, authException);
        Map responseMap = new HashMap();
        responseMap.put("success", false);
        responseMap.put("resultMsg", "인증 실패했습니다");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(responseMap);
        response.getWriter().println(json);
        System.out.println(json);
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("myproject");
        super.afterPropertiesSet();
    }
}
