package com.sjkim.web.controller;

import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    MethodSecurityInterceptor methodSecurityInterceptor;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
