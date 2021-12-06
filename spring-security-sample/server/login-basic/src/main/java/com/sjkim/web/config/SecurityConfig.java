package com.sjkim.web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder()
                        .username("user2")
                        .password("2222")
                        .roles("USER"))
                .withUser(User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("3333")
                        .roles("ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> {
            request.antMatchers("/").permitAll()
                    .anyRequest().authenticated();
        });
        http.formLogin(login -> login.loginPage("/login").permitAll()
                .defaultSuccessUrl("/", false));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()) // CSS 깨짐현상 해결
        ;
    }
}
