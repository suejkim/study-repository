package com.sjkim.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;


@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("title", "HOME");
        modelAndView.addObject("today", LocalDate.now());
        log.trace(">>TRACE title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.info(">>INFO title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.debug(">>DEBUG title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.error(">>ERROR title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        return modelAndView;
    }
}
