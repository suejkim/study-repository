### Spring MVC (작업중)

#### Spring MVC 구조
- Servlet에서 사용하는 HttpServletRequest, HttpServletResponse 등을 사용하지 않아도 Spring MVC로 프로젝트 구현 가능. 

- web.xml
- dispatcher-servlet.xml(servlet-context.xml)
    - Web
    - Spring MVC
- applicationContext.xml(root-context.xml)
    - POJO
    - Spring Core, Mybatis
    - 정의된 Bean들은 스프링 Context안에서 생성됨

#### 동작방식

#### 컨트롤러 구현
- Return Type
    - ModelAndView
    - String : VIEW name 
- Request Parameter
    - Model(= ModelMap) : 컨트롤러 파라미터에 Model을 추가하여 데이터를 담을 수 있다.
        - model.addAttribute("model key", "value");
    - HttpServletRequest
    - @RequestParam     

- Annotation
    - @Controller
    - @RequestMapping: 요청 경로 설정(url)
        - 요청/응답 content type이 다른 경우
            - consumes(consumes = MediaType.APPLICATION_JSON_VALUE): 요청 헤더 Content-Type가 application/json일 떄만 처리
            - produces(produces = MediaType.APPLICATION_JSON_VALUE): 요청 헤더 Accept가 application/json일 때만 처리
        - HTTP Method 지정: RequestMethod.GET, RequestMethod.POST ...
    - @PathVariable: 경로 변수
    
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
