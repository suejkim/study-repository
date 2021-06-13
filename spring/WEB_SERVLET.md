Web & Servlet (작업중)
===
---
1. 웹 애플리케이션
2. 서블릿(Servlet)
---

#### 1. 웹 애플리케이션
<img src="https://www.educative.io/api/edpresso/shot/5043667712606208/image/5659644338896896
" width="80%" title="spring_modules" alt="spring_modules"></img>

##### 웹 서버
- HTML이나 이미지 등의 정적인 컨텐츠에 대한 클라이언트 요청을 다룸 → 정적인 페이지
##### 웹 애플리케이션 서버(WAS)
- 사용자의 입력을 받아서 결과 데이터를 보임. HTML을 동적으로 만듦 → 동적인 페이지
- **웹 서버와 별도로 WAS를 두어서 동적인 처리를 하도록 위임**
- 좀 더 간단하게는 웹 서버를 두지 않고 모든 요청은 WAS가 받도록 함 
<br/>

#### 2. 서블릿(Servlet)
##### 정의
- Servlet, JSP는 자바 웹 애플리케이션을 만들기 위한 도구
##### 웹 컨테이너(서블릿 컨테이너)
- Servlet과 JSP를 실행할 수 있는 소프트웨어(Apache Tomcat, jetty, JEUS)
- Servlet과 웹 서버가 통신할 수 있도록 API 제공하여 비즈니스 로직 구현에 집중할 수 있음
- JSP 지원
- **Servlet 자원 관리(생명주기 관리)**
    - init() 컨테이너 시작시 실행하여 servlet 인스턴스 초기화
    - service() client 요청 처리
    - destroy() servlet 종료
##### 서블릿 컨테이너 실행과정 - (Apache Tomcat의 요청 처리 과정)
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRB_PTvJcETBxFKq3AphsSFY3ijNi3s7C8qQ&usqp=CAU
" title="spring_modules" alt="spring_modules"></img>
1. Client가 URL을 통해 요청
2. HTTP Request가 서블릿 컨테이너로 전송됨
3. 서블릿 컨테이너는 HttpServletRequest, HttpServletResponse 객체를 생성
4. web.xml(DD, 배포서술자)은 URL을 분석하여 URL과 대응하는 Servlet Class를 검색
5. 서블릿 컨테이너는 service()를 호출하며 HTTP method에 따라 doGet(), doPost()를 호출
6. 페이지를 만든 후, 웹 서버는 HttpServletResponse 객체를 HTTP Response로 전환하여 보냄
7. HttpServletRequest, HttpServletResponse 객체가 소멸

---
> educative.io/edpresso/web-server-vs-application-server 
https://www.jitendrazaa.com/blog/java/servlet/how-container-handles-the-servlet-request/
Head First Servlets&JSP
