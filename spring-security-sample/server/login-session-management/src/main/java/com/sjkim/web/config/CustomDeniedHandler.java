package com.sjkim.web.config;

import com.sjkim.web.controller.CannotAccessUserPageException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (accessDeniedException instanceof CannotAccessUserPageException) {
            request.getRequestDispatcher("/access-denied").forward(request, response);
        } else {
            request.getRequestDispatcher("/access-denied2").forward(request, response);
        }
    }
}
