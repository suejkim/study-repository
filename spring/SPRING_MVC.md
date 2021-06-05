Spring MVC (작업중)
====
---
1. MVC Pattern(Model-View-Controller)
2. Spring MVC
3. Spring MVC 설정
4. Controller 구현
---

#### 1. MVC Pattern(Model-View-Controller)
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/MVC-Process.svg/400px-MVC-Process.svg.png" width="30%" height="40%" title="mvc_pattern" alt="mvc_pattern"></img>

- View와 Model을 분리
- 사용자의 요청 처리 및 흐름제어는 Controller 담당
- 유지보수가 쉬워지며 애플리케이션 확장성 증가
##### 구성요소
- Model
    - 데이터, 비즈니스 로직 처리
- View
    - 사용자에게 보일 Model 표현
- Controller
    - 사용자의 입력을 받아 Model을 조작하여 사용자의 요청을 처리
    - View와 Model의 결합을 분리시켜 유연하게 함
- → 사용자는 View에 요청을 하게 되면 Controller는 Model로 사용자의 요청을 처리한다. Model을 통해 비즈니스 로직 처리가 이루어진 데이터는 Controller가 결과 데이터를 View에 전달하여 사용자가 볼 수 있게 한다.
</br>

#### 2. Spring MVC
- Servlet에서 사용하는 HttpServletRequest, HttpServletResponse 등을 사용하지 않아도 Spring MVC로 프로젝트 구현 가능
##### 동작방식
<img src="https://www.egovframe.go.kr/wiki/lib/exe/fetch.php?media=egovframework:rte:ptl:springmvcstructure.jpg" width="70%" height="30%" title="spring_mvc" alt="spring_mvc"></img>
1. Client의 Request를 DispatcherServlet이 받음 (web.xml)
    ``` text
    Front-Controller Pattern
    : DispatcherServlet
    ```
2. HandlerMapping이 요청된 URL과 매핑된 컨트롤러를 찾고 HandlerAdapter로 컨트롤러 동작시킴
    > DispatcherServlet의 처리 요청을 변환하여 컨트롤러에 전달하고, 그 결과를 DispatcherServlet이 요구하는 형식으로 변환. 웹 브라우저 캐시 등 설정
3. Controller에서 Request를 처리하는 로직을 타고 다양한 타입으로 결과를 반환
4. ViewResolver는 Controller가 반환한 결과를 어떤 View로 처리할지 결정
5. 결과 데이터는 View를 통해 Response로 만들어져 DispatcherServlet을 통해 Client로 전송됨
</br>

#### 3. Spring MVC 설정
##### 내부구조
- web.xml[WebConfig.java]
    - Tomcat 구동 설정
    - 내부적으로 Spring Container 생성
    - dispatcher(서블릿이름)-servlet.xml을 스프링 설정 파일로 사용
- dispatcher-servlet.xml(servlet-context.xml)[ServletConfig.java]
    - Spring MVC 관련 설정
- applicationContext.xml(root-context.xml)[RootConfig.java]
    - POJO
    - Spring Core, Mybatis 등 정의
    - 정의된 Bean들은 스프링 Context안에서 생성됨

##### 순서
1. web.xml
2. applicaionContext.xml
    - 여기서 정의된 Bean들이 Spring Context안에 생성되어 dependency가 이루어짐
3. dispatcher-servlet.xml
    ``` text
    org.springframework.beans.factory.xml.XmlBeanDefinitionReader.doLoadBeanDefinitions(XmlBeanDefinitionReader.java:393) --- Loaded 20 bean definitions from ServletContext resource [/WEB-INF/dispatcher-servlet.xml]
    ```
    - DispatcherServlet이 AbstractApplicationContext 이용해서 로딩
    - 여기서 만들어진 Bean은 기존 context에 있는 Bean과 연동

##### 설정
1. create gradle project
``` bash
mkdir spring-mvc-example
```
``` bash
cd spring-mvc-example
```
``` bash
# gradle project 생성
gradle init --type java-application 
```
```
.
├── app
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── sjkim
│       │   │           └── App.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── com
│           │       └── sjkim
│           │           └── AppTest.java
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```
- 디렉토리 추가 후
```
.
├── app
│   ├── build
│   ├── build.gradle
│   ├── out
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── sjkim
│       │   │           └── home
│       │   │               └── HomeController.java
│       │   ├── resources
│       │   │   └── log4j.properties
│       │   └── webapp
│       │       └── WEB-INF
│       │           ├── applicationContext.xml
│       │           ├── dispatcher-servlet.xml
│       │           ├── views
│       │           │   ├── home1.jsp
│       │           └── web.xml
│       └── test
│           ├── java
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

2. build.gradle
``` groovy
plugins {
    id 'application'
    id 'java'
    id 'war'
}

repositories {
    mavenCentral()
}

ext {
    lombokVersion = '1.18.20'
    slf4jVersion = '1.7.30'
    springVersion = '5.3.6'
}

dependencies {
    implementation ("org.springframework:spring-webmvc:${springVersion}")
    implementation ("org.springframework:spring-core:${springVersion}")
    implementation ("org.springframework:spring-web:${springVersion}")

    implementation ("org.projectlombok:lombok:${lombokVersion}")
    implementation ("org.slf4j:slf4j-api:${slf4jVersion}")
    implementation ("org.slf4j:slf4j-log4j12:${slf4jVersion}")
    annotationProcessor ("org.projectlombok:lombok:${lombokVersion}")
    implementation ("javax.servlet:javax.servlet-api:4.0.1")

    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation ("org.hamcrest:hamcrest-core:2.2")
}
```
3. web.xml 또는 javaCode Config(Servlet 3.0 이상)
    - DispatcherServlet 설정
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <!-- 컨트롤러 url 매핑 경로를 전체 경로로 사용 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!-- 요청 파라미터의 캐릭터 인코딩 지정. 서블릿 필터 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```
``` java
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
   @Override
   protected Class<?>[] getRootConfigClasses() {
       return new Class[]{RootConfig.class};
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
       return new Class[]{ServletConfig.class};
   }

   @Override
   protected String[] getServletMappings() {
       return new String[]{"/"};
   }
}
```
4. dispatcher-servlet.xml 또는 ServletConfig.java
    - Interceptor, Controller 클래스, ViewResolver 설정
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
	    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd">

    <!-- HandlerMapping, HandlerAdapter 설정 -->
    <mvc:annotation-driven/>

    <!--    <context:component-scan base-package="com.sjkim.home"/>-->
    <bean id="homeController" class="com.sjkim.home.HomeController"/>

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```
``` java
@EnableWebMvc
@ComponentScan(basePackages = {"com.sjkim.home"})
public class ServletConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }
}
```
5. applicationContext.xml 또는 RootConfig.java
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:task="http://www.springframework.org/schema/task"
       xsi:schemaLocation="
	    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd">

    <!-- 필요한 Bean 생성 -->
</beans>
```    
``` java
@Configuration
public class RootConfig {
    // 필요한 Bean 생성
}
```
</br>

#### 4. Controller 구현
##### Return Type
- ModelAndView: Model, View 함께 리턴
- String: View name (jsp 이용시 jsp 파일 이름)
- void: Controller에서 응답 데이터 생성
- 응답객체(VO, DTO): (@ResponseBody 적용 시) 주로 JSON 타입
- ResponseEntity: 결과 데이터, HttpHeader 가공 

##### Request Parameter
- Model(= ModelMap): 주로 파라미터로 추가적인 데이터를 담을 경우 사용(페이지 번호를 파라미터로 받고 결과 데이터를 View로 전달 시)
    - model.addAttribute("model key", "value");
- HttpServletRequest
- @RequestParam
- @ModelAttribute: 어노테이션이 적용된 파라미터는 무조건 Model에 담아서 전달됨
- @RequestBody: Request Body → 자바객체    
- @PathVariable: 경로 변수
- 요청 객체(VO, DTO): Spring MVC에서 Java Beans는 그대로 View에 전달될 수 있음

##### Annotation
- @Controller
- @RequestMapping: 요청 경로 설정(url)
    - 요청/응답 content type이 다른 경우
        - consumes(consumes = MediaType.APPLICATION_JSON_VALUE): 요청 헤더 Content-Type가 application/json일 떄만 처리
        - produces(produces = MediaType.APPLICATION_JSON_VALUE): 요청 헤더 Accept가 application/json일 때만 처리
    - HTTP Method 지정: RequestMethod.GET, RequestMethod.POST ...
    - @GetMapping
    - @PostMapping

- @ResponseBody: 자바객체 → Response Body (주로 XML, JSON으로 변환 시)

``` java
package com.sjkim.home;

@Slf4j
@Controller
public class HomeController { // 컨트롤러 구현 테스트

    /**
     * @return ModelAndView
     */
    @RequestMapping("/modelandview-return-type")
    public ModelAndView modelAndViewReturnType() {
        ModelAndView modelAndView = new ModelAndView("modelAndViewReturnPage");
        modelAndView.addObject("title", "ModelAndView 테스트");
        modelAndView.addObject("today", LocalDate.now());
        return modelAndView;
    }

    /**
     * stringReturnPage.jsp 페이지 호출
     *
     * @return String
     */
    // @RequestMapping("/string-return-type")
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

    /**
     * @param request HttpServletRequest 파라미터
     * @param model
     * @return
     */
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
    /**
     * @param id
     * @param model
     * @return
     * @RequestParam
     */
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

    // @ModelAttribute 객체의 이름을 지정하고 싶을 때 사용, 파라미터를 무조건 Model에 넣을 경우 사용
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

    // HttpServletResponse을 이용한 리다이렉트 테스트
    @RequestMapping(value = "/registered")
    @SneakyThrows
    public void redirectTest(HttpServletResponse response) {
        response.sendRedirect("main");
    }

    /**
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/response-entity-return-type")
    public ResponseEntity<String> responseEntityReturnType() {
        String jsonData = "{\"title\":\"Spring\"}";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        return new ResponseEntity<>(jsonData, httpHeaders, HttpStatus.OK);
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }

    @RequestMapping(value = "/guest3", method = RequestMethod.GET)
    public String guest3() {
        return "guestForm3";
    }

    /**
     *
     * @ResponseBody 요청 데이터가 그대로 응답 데이터로 전송
     * @RequestBody
     *
     * @param body
     * @return
     */
    @PostMapping("/register3")
    @ResponseBody
    public String register(@RequestBody String body) {
        return body;
    }

    /**
     * void Return Type
     * @param response
     * @throws IOException
     */
    @RequestMapping("/void-return-type")
    public void voidReturnType(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write("안녕");
        writer.flush();
    }
}
```