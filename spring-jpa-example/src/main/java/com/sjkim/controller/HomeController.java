package com.sjkim.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/member")
    public void doMember() {
//        return "member";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String doAdmin() {
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/error")
    public String accessDenied(Authentication auth, Model model) {
        model.addAttribute("auth", auth);
        return "error";
    }
}
