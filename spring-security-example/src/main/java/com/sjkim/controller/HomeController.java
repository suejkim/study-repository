package com.sjkim.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
