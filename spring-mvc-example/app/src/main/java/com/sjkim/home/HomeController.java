package com.sjkim.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping("/modelandview-return-type")
    public ModelAndView modelAndViewReturnType() {
        ModelAndView modelAndView = new ModelAndView("modelAndViewReturnPage");
        modelAndView.addObject("title", "ModelAndView 테스트");
        modelAndView.addObject("today", LocalDate.now());
        // Log test
        log.trace(">>TRACE title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.info(">>INFO title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.debug(">>DEBUG title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        log.error(">>ERROR title: {}, today: {}", modelAndView.getModel().get("title"), modelAndView.getModel().get("today"));
        return modelAndView;
    }

    /**
     * stringReturnPage.jsp 페이지 호출
     *
     * @return String
     */
//    @RequestMapping("/string-return-type")
    // HTTP method 방식 지정
    // 배열 이용: 여러 경로를 한 메소드에 처리
    @RequestMapping(value = {"/string-return-type", "/index"}, method = RequestMethod.GET)
    public String stringReturnType() {
        return "stringReturnPage";
    }

    /**
     * Request Parameter로 Model
     *
     * @param model
     * @return String
     */
    @RequestMapping("/model-parameter")
    public String modelParameter(Model model) {
        model.addAttribute("title", "Model Parameter 테스트")
                .addAttribute("now", LocalDateTime.now());
        return "modelParameterPage";
    }

    /**
     * Request Parameter로 ModelMap (Model과 동일)
     *
     * @param modelMap
     * @return String
     */
    @RequestMapping("/modelmap-parameter")
    public String modelMapParameter(ModelMap modelMap) {
        modelMap.addAttribute("title", "ModelMap Parameter 테스트")
                .addAttribute("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "modelMapParameterPage";
    }

    /**
     * @param id    @PathVariable : REST API방식의 개발이 증가함에 따라 경로 변수를 주로 사용. Long 타입으로 변환하여 파라미터에 전달.
     *              Long으로 변환할 수 없을 경우 400 Error
     * @param model
     * @return
     */
    @RequestMapping(value = "/pathvariable-parameter/{id}")
    public String pathVariableParameter(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "pathVariablePage";
    }

    @RequestMapping(value = "/httpservletrequest-parameter", produces = MediaType.TEXT_HTML_VALUE)
    public String httpServletRequestParameter(HttpServletRequest request, Model model) {
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
        return "httpServletRequestParameterPage";
    }

    // 요청 파라미터 필수 여부 @RequestParam(required = false)
    // @RequestParam(defaultValue = "1") null 체크 생략 가능. 요청 파라미터가 존재하지 않을 경우 기본값
    @RequestMapping("/requestparam-parameter")
    public String requestParamParameter(@RequestParam(required = false, defaultValue = "1") Long id, Model model) {
        model.addAttribute("id", id);
        return "requestParamParameterPage";
    }

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public String guest() {
        return "guestForm";
    }

    // GuestDto 객체를 파라미터에 추가하면 그 객체의 setter을 호출하여 파라미터를 전달
    // View에 전달할 Model에 포함됨
    // @ModelAttribute와 동일
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerGuest(GuestDto guestDto) {
        log.info("name {}", guestDto.getName());
        log.info("memo {}", guestDto.getMemo());
        return "registeredGuest";
    }

    @RequestMapping(value = "/guest2", method = RequestMethod.GET)
    public String guest2() {
        return "guestForm2";
    }

    // @ModelAttribute 객체의 이름을 지정하고 싶을 때 사용
    @RequestMapping(value = "/register2", method = RequestMethod.POST)
    public String registerGuest2(@ModelAttribute("guest") GuestDto guestDto) {
        return "registeredGuest2";
    }

    // 리다이렉트: 클라이언트의 요청을 처리한 후 다른 페이지로 이동
    // redirect 뒤에 경로는 웹 애플리케이션 URL 경로
    // /로 시작하지 않으면 @RequestMapping 경로를 기준 상대경로로 리다이렉트
    @RequestMapping(value = "/completed")
    public String redirectTest() {
        return "redirect:main";
    }

    @RequestMapping(value = "/main")
    public String mainTest() {
        return "mainPage";
    }
}
