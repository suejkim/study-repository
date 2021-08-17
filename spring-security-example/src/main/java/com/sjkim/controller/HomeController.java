package com.sjkim.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    public static final String HOME = "/home";
    public static final String MEMBER = "/member";
    public static final String ADMIN = "/admin";
    public static final String ERROR = "/error";

    @GetMapping(value = HOME)
    public String home() {
        return "home";
    }

    @GetMapping(value = MEMBER)
    public String doMember() {
        return "member";
    }

    @GetMapping(value = ADMIN)
    public String doAdmin() {
        return "admin";
    }

    @GetMapping(value = ERROR)
    public String accessDenied(Authentication auth, Model model) {
        model.addAttribute("auth", auth);
        return "error";
    }
}
