package com.sjkim.web.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

// 로그인할 때 들어오는 요청 정보들을 Custom 할 수 있음
public class CustomAuthDetail implements AuthenticationDetailsSource<HttpServletRequest, RequestInfo> {
    @Override
    public RequestInfo buildDetails(HttpServletRequest context) {
        return RequestInfo.builder()
                .remoteIP(context.getRemoteAddr())
                .sessionId(context.getSession().getId())
                .loginTime(LocalDateTime.now())
                .build();
    }
}
