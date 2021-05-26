## Spring MVC (작업중)
---
#### MVC Pattern(Model-View-Controller)

#### Spring MVC
- Servlet에서 사용하는 HttpServletRequest, HttpServletResponse 등을 사용하지 않아도 Spring MVC로 프로젝트 구현 가능
- 3-tier
    - Presentation Tier(화면 계층)
    - Business Tier(비즈니스 계층)
    - Persistence Tier(영속 계층 혹은 데이터 계층)s

#### 동작방식
<img src="https://www.egovframe.go.kr/wiki/lib/exe/fetch.php?media=egovframework:rte:ptl:springmvcstructure.jpg" width="70%" height="30%" title="spring_mvc" alt="spring_mvc"></img>
1. Client의 Request를 DispatcherServlet이 받음 (web.xml)
    ```text
    Front-Controller Pattern
    : DispatcherServlet
    ```
2. HandlerMapping이 요청된 URL과 매핑된 컨트롤러를 찾고 HandlerAdapter로 컨트롤러 동작시킴
    > DispatcherServlet의 처리 요청을 변환하여 컨트롤러에 전달하고, 그 결과를 DispatcherServlet이 요구하는 형식으로 변환. 웹 브라우저 캐시 등 설정
3. Controller에서 Request를 처리하는 로직을 타고 다양한 타입으로 결과를 반환
4. ViewResolver는 Controller가 반환한 결과를 어떤 View로 처리할지 결정
5. 결과 데이터는 View를 통해 Response로 만들어져 DispatcherServlet을 통해 Client로 전송됨

 


#### 내부구조
```text
- web.xml[WebConfig.java ?]
    - Tomcat 구동 설정
    - 내부적으로 Spring Container 생성
    - dispatcher(서블릿이름)-servlet.xml을 스프링 설정 파일로 사용
- dispatcher-servlet.xml(servlet-context.xml)[ServletConfig.java]
    - Web
    - Spring MVC
- applicationContext.xml(root-context.xml)[RootConfig.java]
    - POJO
    - Spring Core, Mybatis
    - 정의된 Bean들은 스프링 Context안에서 생성됨
```
순서
1. web.xml
2. applicaionContext.xml
    - 여기서 정의된 Bean들이 Spring Context안에 생성되어 dependency가 이루어짐
3. dispatcher-servlet.xml
    ``` text
    org.springframework.beans.factory.xml.XmlBeanDefinitionReader.doLoadBeanDefinitions(XmlBeanDefinitionReader.java:393) --- Loaded 20 bean definitions from ServletContext resource [/WEB-INF/dispatcher-servlet.xml]
    ```
    - DispatcherServlet이 AbstractApplicationContext 이용해서 로딩
    - 여기서 만들어진 Bean은 기존 context에 있는 Bean과 연동

#### 설정
1. build.gradle
2. DispatcherServlet 설정
    - web.xml 추가 OR javaConfig(Servlet 3.0 이상)
3. dispatcher-servlet.xml
    - Interceptor, Controller 클래스 설정


#### 컨트롤러 구현
- Return Type
    - ModelAndView
    - String: View name (jsp 이용시 jsp 파일 이름)
    - void
    - VO, DTO: 주로 JSON 타입
    - ResponseEntity: 결과 데이터, HttpHeader 가공 
- Request Parameter
    - Model(= ModelMap): 주로 파라미터로 추가적인 데이터를 담을 경우 사용(페이지 번호를 파라미터로 받고 결과 데이터를 View로 전달 시)
        - model.addAttribute("model key", "value");
    - HttpServletRequest
    - @RequestParam
    - @ModelAttribute: 어노테이션이 적용된 파라미터는 무조건 Model에 담아서 전달됨
    - @RequestBody: Request Body -> 자바객체    

- Annotation
    - @Controller
    - @RequestMapping: 요청 경로 설정(url)
        - 요청/응답 content type이 다른 경우
            - consumes(consumes = MediaType.APPLICATION_JSON_VALUE): 요청 헤더 Content-Type가 application/json일 떄만 처리
            - produces(produces = MediaType.APPLICATION_JSON_VALUE): 요청 헤더 Accept가 application/json일 때만 처리
        - HTTP Method 지정: RequestMethod.GET, RequestMethod.POST ...
        - @GetMapping
        - @PostMapping
    - @PathVariable: 경로 변수
    - @ResponseBody: 자바객체 -> Response Body (주로 XML, JSON으로 변환 시)
    
---
- Gradle Project
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
