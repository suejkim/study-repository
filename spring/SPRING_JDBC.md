Spring JDBC (작업중)
===
---
1. 전통적인 JDBC 방식으로 개발
2. Template Method Pattern 적용하여 개발
3. JdbcTemplate 사용하여 개발
---

#### 1. 전통적인 JDBC 방식으로 개발
##### 테스트 코드 및 코드 평가
- 테스트 코드
    - JUnit Test
- 코드 평가
    - jacoco: Java code에 대한 code coverage 제공
    - sonarqube: 정적 분석 기술을 사용하여 code의 품질을 향상시키는 것을 목적으로 하는 툴
    - sonarqube와 jacoco를 연동하면 sonarqube 서버에서도 coverage를 확인할 수 있음 
        - SonarQube 로컬 인스턴스 설치 https://docs.sonarqube.org/latest/setup/get-started-2-minutes/
        ``` bash
        brew install sonarqube
        brew install sonar-scanner
        brew services start sonarqube
        ```
        - build.gradle
        ``` groovy
        ```
        ``` bash
        gradle :testSonarqube
        ``` 
        - 로그인
        ``` text
        http://localhost:9000
        id: admin, pwd: admin
        ```
##### DB: mariadb
- docker-compose.yml
    ``` yml
    ```
    ``` bash
    gradle :composeUp
    docker exec -it mariadb-study bash
    mysql -u sjkim -p
    ```
##### project spring-jdbc-example 의 문제점
- StudentService의 각 method마다 DB Driver 객체 정보를 얻음
- jdbc url, id, password의 반복 
- Connection 연결과 close가 반복
- PreparedStatement close가 반복
- Connection, PreparedStatement의 try-with-resources 미적용

#### 2. Template Method Pattern 적용하여 개발
<img src="https://reactiveprogramming.io/books/patterns/img/patterns-articles/templete-method-diagram.png" width="60%" alt="template_method"/>

- 정의
    - Template(틀) Method(메소드): 일련의 단계(알고리즘)를 메소드에서 관장
    - 그 알고리즘의 여러 단계 중 일부를 서브클래스에서 재정의하여 구현
    - 추상클래스에서 알고리즘을 관리하고 필요할 때 서브클래스의 메소드를 호출 → ```Hollywood Principle```
- 왜 사용하는가?
    - 중복된 코드 제거, 코드 재사용
    - 알고리즘 변경시 알고리즘이 작성된 Template Method만 수정하면 됨
    - 같은 알고리즘을 구현할 클래스를 쉽게 추가할 수 있음
- 구현
    - 추상클래스
        - Template Method 작성. final로 선언하여 서브클래스에서 각 메소드를 변경하는 것을 방지함
        - 서브클래스에서 구현할 추상메소드 작성
    - 서브클래스
        - 추상클래스의 추상메소드를 재정의하여 구현
    - Hook(후크) : 알고리즘에서 특정 단계를 선택적으로 적용하고자 할 때 사용. 서브클래스에서 재정의하여 사용할 수 있고, 재정의하지 않으면 추상클래스에서 작성된 코드가 실행됨

---
> https://docs.gradle.org/current/userguide/jacoco_plugin.html 
https://plugins.gradle.org/plugin/org.sonarqube 
https://tomgregory.com/how-to-measure-code-coverage-using-sonarqube-and-jacoco/
https://kwonnam.pe.kr/wiki/gradle/sonarqube
https://woowabros.github.io/experience/2020/02/02/jacoco-config-on-gradle-project.html
Head First Design Pattern 
https://reactiveprogramming.io/books/patterns/img/patterns-articles/templete-method-diagram.png
