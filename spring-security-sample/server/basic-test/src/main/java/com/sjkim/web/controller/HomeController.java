package com.sjkim.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/auth")
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public SecurityMessage user() {
        return SecurityMessage.builder()
                .authentication(SecurityContextHolder.getContext().getAuthentication())
                .message("유저정보")
                .build();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SecurityMessage admin() {
        return SecurityMessage.builder()
                .authentication(SecurityContextHolder.getContext().getAuthentication())
                .message("관리자정보")
                .build();
    }
}
