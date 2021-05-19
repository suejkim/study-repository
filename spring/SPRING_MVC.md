### Spring MVC

- Servlet에서 사용하는 HttpServletRequest, HttpServletResponse 등을 사용하지 않아도 Spring MVC로 프로젝트 구현 가능. 

- web.xml
- servlet-context.xml(dispatcher-servlet.xml)
    - Web
    - Spring MVC
- root-context.xml(applicationContext.xml)
    - POJO
    - Spring Core, Mybatis

- @Controller, @RequestMapping, Model
    - @Controller
    - @RequestMapping : 요청 경로 설정
        - return String : view name 
    - Model : 컨트롤러 파라미터에 Model을 추가하여 데이터를 담을 수 있다.
        - model.addAttribute("model key", "value");
        - = ModelMap