package com.sjkim.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Controller
public class HomeController {

    /**
     * @return ModelAndView
     */
    @RequestMapping("/home1")
    public ModelAndView home1() {
        ModelAndView modelAndView = new ModelAndView("home1");
        modelAndView.addObject("title", "HOME1");
        modelAndView.addObject("today", LocalDate.now());
        // Log test
        log.trace(">>TRACE title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.info(">>INFO title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.debug(">>DEBUG title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.error(">>ERROR title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        return modelAndView;
    }

    /**
     * home2.jsp 페이지 호출
     *
     * @return String
     */
//    @RequestMapping("/home2")
    // HTTP method 방식 지정
    // 배열 이용: 여러 경로를 한 메소드에 처리
    @RequestMapping(value = {"/home2", "/index"}, method = RequestMethod.GET)
    public String home2() {
        return "home2";
    }

    /**
     * Request Parameter로 Model
     *
     * @param model
     * @return String
     */
    @RequestMapping("/home3")
    public String home3(Model model) {
        model.addAttribute("title", "HOME3")
                .addAttribute("now", LocalDateTime.now());
        return "home3";
    }

    /**
     * Request Parameter로 ModelMap (Model과 동일)
     *
     * @param modelMap
     * @return String
     */
    @RequestMapping("/home4")
    public String home4(ModelMap modelMap) {
        modelMap.addAttribute("title", "HOME4")
                .addAttribute("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "home4";
    }

    /**
     * @param id    @PathVariable : REST API방식의 개발이 증가함에 따라 경로 변수를 주로 사용. Long 타입으로 변환하여 파라미터에 전달.
     *              Long으로 변환할 수 없을 경우 400 Error
     * @param model
     * @return
     */
    @RequestMapping(value = "/home5/{id}")
    public String home5(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "home5";
    }

    @RequestMapping(value = "/home6", produces = MediaType.TEXT_HTML_VALUE)
    public String home6(HttpServletRequest request, Model model) {
        String title = request.getParameter("title"); // 파라미터를 통해 값을 받아 옴
        String method = request.getMethod();
        String queryString = request.getQueryString();
        request.setAttribute("attribute", "set"); // setAttribute 속성을 이용해야
        Object attribute = request.getAttribute("attribute"); // getAttribute로 받아올 수 있음
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        model.addAttribute("title", title);
        model.addAttribute("method", method);
        model.addAttribute("queryString", queryString);
        model.addAttribute("attribute", attribute);
        model.addAttribute("requestURI", requestURI);
        model.addAttribute("requestURL", requestURL);
        return "home6";
    }

    // 요청 파라미터 필수 여부 @RequestParam(required = false)
    // @RequestParam(defaultValue = "1") null 체크 생략 가능. 요청 파라미터가 존재하지 않을 경우 기본값
    @RequestMapping("/home7")
    public String home7(@RequestParam(required = false, defaultValue = "1") Long id, Model model) {
        model.addAttribute("id", id);
        return "home7";
    }

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public String guest() {
        return "guestForm";
    }

    // GuestDto 객체를 파라미터에 추가하면 그 객체의 setter을 호출하여 파라미터를 전달
    // View에 전달할 Model에 포함됨
    // @ModelAttribute와 동일 (왜?)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerGuest(GuestDto guestDto) {
        log.info("name {}", guestDto.getName());
        log.info("memo {}", guestDto.getMemo());
        return "registeredGuest";
    }
}
