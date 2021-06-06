package com.sjkim.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ExampleFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(ExampleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    // Filter 정의. 사용 예) 인코딩
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        logger.info("필터 동작 전");
        servletRequest.setCharacterEncoding("UTF-8"); // 파라미터 인코딩
        servletResponse.setContentType("text/html;charset=utf-8"); // 브라우저에 보일 데이터 인코딩
//        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("필터 동작 후");
    }

    @Override
    public void destroy() {

    }
}