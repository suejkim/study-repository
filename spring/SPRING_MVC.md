### Spring MVC

- @Controller, @RequestMapping, Model
    - @Controller
    - @RequestMapping : 요청 경로 설정
        - return String : view name 
    - Model : 컨트롤러 파라미터에 Model을 추가하여 데이터를 담을 수 있다.
        - model.addAttribute("model key", "value");
        - = ModelMap