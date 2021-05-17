package com.sjkim.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/home1")
    public ModelAndView home1() {
        ModelAndView modelAndView = new ModelAndView("home1");
        modelAndView.addObject("title", "HOME1");
        modelAndView.addObject("today", LocalDate.now());
        log.trace(">>TRACE title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.info(">>INFO title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.debug(">>DEBUG title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.error(">>ERROR title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        return modelAndView;
    }

    @RequestMapping("/home2")
    public String home2() {
        return "home2";
    }

    @RequestMapping("/home3")
    public String home3(Model model) {
        model.addAttribute("title", "HOME3")
                .addAttribute("now", LocalDateTime.now());
        return "home3";
    }

    @RequestMapping("/home4")
    public String home4(ModelMap modelMap) {
        modelMap.addAttribute("title", "HOME4")
                .addAttribute("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "home4";
    }
}
