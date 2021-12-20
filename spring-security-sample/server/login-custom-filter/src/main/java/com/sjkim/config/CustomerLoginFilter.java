package com.sjkim.config;

import com.sjkim.student.StudentAuthenticationToken;
import com.sjkim.teacher.TeacherAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerLoginFilter extends UsernamePasswordAuthenticationFilter {

    public CustomerLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        username = (username != null) ? username : "";
        username = username.trim();
        String type = request.getParameter("type");
        if (type == null || !type.equals("teacher")) {
            // student
            StudentAuthenticationToken token = StudentAuthenticationToken.builder().credentials(username).build();
            return this.getAuthenticationManager().authenticate(token);
        } else {
            // teacher
            TeacherAuthenticationToken token = TeacherAuthenticationToken.builder().credentials(username).build();
            return this.getAuthenticationManager().authenticate(token);
        }
    }
}
