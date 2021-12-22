package com.sjkim.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("user1").password("1111").roles("USER").build());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // POST 요청이 있을 경우 csrf().disable() 처리 필요
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        // SPA -> csrf().disable()
        // Server가 동시에 웹페이지 서비스 -> csrf를 enable
        // 서로 다른 인증 정책이 적용될 것
    }
}
