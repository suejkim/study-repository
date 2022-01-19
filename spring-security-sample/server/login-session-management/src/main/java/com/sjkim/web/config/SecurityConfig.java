package com.sjkim.web.config;

import com.sjkim.user.service.SpUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;
import javax.sql.DataSource;
import java.time.LocalDateTime;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SpUserService spUserService;
    private final DataSource dataSource;

    public SecurityConfig(SpUserService spUserService, DataSource dataSource) {
        this.spUserService = spUserService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(spUserService);
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER"); // 상위 ROLE은 무조건 왼쪽에 (부등호 주의)
        return roleHierarchy;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // 테스트에서만 사용
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> {
            request.antMatchers("/").permitAll()
                    .anyRequest().authenticated();
        });
        http.formLogin(login ->
                        login.loginPage("/login").permitAll()
                                .defaultSuccessUrl("/", false)
                                // alwaysUse가 false인 경우, 로그인 후 defualtUrl이 아닌 원래 들어가려고 했던 url로 이동
                                .failureUrl("/login-error"))
                .logout(logout ->
                        logout.logoutSuccessUrl("/"))
                .exceptionHandling(error ->
//                        error.accessDeniedPage("/access-denied")
                        error.accessDeniedHandler(new CustomDeniedHandler()) // .accessDeniedPage 대신에 CustomDeniedHandler
                )
//                .rememberMe() // TokenBasedRememberMe
                .rememberMe(remember -> remember.rememberMeServices(rememberMeServices())) // PersistenceTokenBasedRememberMe
                .sessionManagement(s ->
//                        s.sessionFixation().none() // .none()으로 로그인 후에도 세션을 동일하게 유지하면 세션탈취시 위험함
                        s.sessionFixation().changeSessionId()
                                .maximumSessions(1)
                                .maxSessionsPreventsLogin(false)
                                .expiredUrl("/session-expired"))
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/sessions", "/session/expire", "/session-expired")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()) // CSS 깨짐현상 해결
                .requestMatchers(PathRequest.toH2Console()) // H2 DB 사용시
        ;
    }

    // 세션 만료/생성 확인 위해 ServletListener 등록
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher() {
            @Override
            public void sessionCreated(HttpSessionEvent event) {
                super.sessionCreated(event);
                System.out.printf("===>> [%s] 세션 생성됨 %s \n", LocalDateTime.now(), event.getSession().getId());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent event) {
                super.sessionDestroyed(event);
                System.out.printf("===>> [%s] 세션 만료됨 %s \n", LocalDateTime.now(), event.getSession().getId());
            }

            @Override
            public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
                super.sessionIdChanged(event, oldSessionId);
                System.out.printf("===>> [%s] 세션 아이디 변경  %s:%s \n", LocalDateTime.now(), oldSessionId, event.getSession().getId());
            }
        });
    }

    @Bean
    PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        try {
            repository.removeUserTokens("1");
        } catch (Exception e) {
            repository.setCreateTableOnStartup(true);
        }
        return repository;
    }

    @Bean
    PersistentTokenBasedRememberMeServices rememberMeServices() {
        var service = new PersistentTokenBasedRememberMeServices("sjkim", spUserService, tokenRepository());
        service.setAlwaysRemember(true);
        return service;
    }

    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
