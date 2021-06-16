Spring JDBC (작업중)
===
---
1. 전통적인 JDBC 방식으로 개발
2. Template Pattern으로 개발
3. JdbcTemplate 사용하여 개발
---

#### 1. 전통적인 JDBC 방식으로 개발
- 테스트 코드 및 코드 평가
    - jacoco: java code에 대한 code coverage 제공
    - sonarqube: 정적 분석 기술을 사용하여 code의 품질을 향상시키는 것을 목적으로 하는 툴
        - SonarQube 로컬 인스턴스 설치 https://docs.sonarqube.org/latest/setup/get-started-2-minutes/
    ``` bash
    brew install sonarqube
    brew install sonar-scanner
    ```
- DB: mariadb
    - docker-compose.yml
    ``` yml
    ```
    ``` bash
    gradle :composeUp
    docker exec -it mariadb-study bash
    ```






---
> https://docs.gradle.org/current/userguide/jacoco_plugin.html 
https://plugins.gradle.org/plugin/org.sonarqube 
https://tomgregory.com/how-to-measure-code-coverage-using-sonarqube-and-jacoco/
https://kwonnam.pe.kr/wiki/gradle/sonarqube