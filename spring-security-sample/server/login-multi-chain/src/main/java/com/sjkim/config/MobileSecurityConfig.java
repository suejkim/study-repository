package com.sjkim.config;

import com.sjkim.student.StudentManager;
import com.sjkim.teacher.TeacherManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 로그보면 두개의 FilterChain으로 구성
// MobileSecurityConfig -> BasicAuthenticationFilter
// SecurityConfig -> CustomerLoginFilter

@Order(1)
@Configuration
public class MobileSecurityConfig extends WebSecurityConfigurerAdapter { // SSR의 로그인정책 SecurityConfig와 비교

    private final StudentManager studentManager;
    private final TeacherManager teacherManager;

    public MobileSecurityConfig(StudentManager studentManager, TeacherManager teacherManager) {
        this.studentManager = studentManager;
        this.teacherManager = teacherManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(studentManager); // StudentManager가 Provider가 됨
        auth.authenticationProvider(teacherManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .csrf().disable() // mobile인 경우
                .authorizeRequests(request ->
                        request.anyRequest().authenticated()) // 모든 페이지 권한 체크
                .httpBasic();
    }
}
