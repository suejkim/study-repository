Spring JDBC (작업중)
===
---
1. 전통적인 JDBC 방식으로 개발
2. Template Pattern으로 개발
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




---
> https://docs.gradle.org/current/userguide/jacoco_plugin.html 
https://plugins.gradle.org/plugin/org.sonarqube 
https://tomgregory.com/how-to-measure-code-coverage-using-sonarqube-and-jacoco/
https://kwonnam.pe.kr/wiki/gradle/sonarqube
https://woowabros.github.io/experience/2020/02/02/jacoco-config-on-gradle-project.html
