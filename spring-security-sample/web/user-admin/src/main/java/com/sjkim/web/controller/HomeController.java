package com.sjkim.web.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        model.addAttribute("sessionId", session.getId());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginForm";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "AccessDenied";
    }

    @GetMapping("/access-denied2")
    public String accessDenied2() {
        return "AccessDenied2";
    }

    // SecurityConfig에 RoleHierarchy 클래스로 ROLE 우선순위 설정해도 되고,
    // 아니면 아래 ROLE_USER, ROLE_ADMIN 설정하면 해당 API는 둘 다 접근이 가능하다.
//    @PreAuthorize(value = "hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/user-page")
    public String userPage() {
        return "UserPage";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/user-page/error") // CannotAccessUserPageException Test
    public String userPageOccurError() {
        if(true) {
            throw new CannotAccessUserPageException();
        }
        return "UserPage";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/admin-page")
    public String adminPage() {
        return "AdminPage";
    }

    @ResponseBody
    @GetMapping("/auth")
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/login-required")
    public String loginRequired() {
        return "LoginRequired";
    }
}
