Spring JDBC (작업중)
===
---
1. 테스트 코드 및 코드 평가
2. 전통적인 JDBC 방식으로 개발 - DriverManager
3. Template Method Pattern 적용하여 개발
4. JdbcTemplate 사용하여 개발
---

#### 1. 테스트 코드 및 코드 평가
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
        plugins {
            id 'java'
            id 'jacoco'
            id "org.sonarqube" version "3.3"
            id "eu.xenit.docker-compose" version "5.3.0"
        }

        group 'com.sjkim'
        version '1.0-SNAPSHOT'

        repositories {
            mavenCentral()
        }

        ext {
            lombokVersion = '1.18.20'
            slf4jVersion = '1.7.30'
            springVersion = '5.3.8'
        }

        dependencies {
            implementation ("org.springframework:spring-jdbc:${springVersion}")
            implementation ("org.mariadb.jdbc:mariadb-java-client:2.7.3")
            implementation ("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.2.0")

            implementation ("org.projectlombok:lombok:${lombokVersion}")
            implementation ("org.slf4j:slf4j-api:${slf4jVersion}")
            implementation ("org.slf4j:slf4j-log4j12:${slf4jVersion}")
            annotationProcessor ("org.projectlombok:lombok:${lombokVersion}")

            testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine")
            testImplementation ("org.junit.jupiter:junit-jupiter-api:5.6.0")
            testImplementation ("org.hamcrest:hamcrest-core:2.2")
            testImplementation ("org.assertj:assertj-core:3.20.1")
        }

        jacoco {
            toolVersion = "0.8.6"
            reportsDirectory = layout.buildDirectory.dir('customJacocoReportDir')
        }

        jacocoTestReport { // 결과를 리포트로 저장
            reports {
                xml.enabled true // sonarqube version 8이상인 경우 true로 변경해야 jacoco랑 연동 가능
                csv.enabled false
                html.destination layout.buildDirectory.dir('jacocoHtml').get().asFile
            }
        }

        test {
            jacoco {
                destinationFile = layout.buildDirectory.file('jacoco/jacocoTest.exec').get().asFile
                classDumpDir = layout.buildDirectory.dir('jacoco/classpathdumps').get().asFile
            }
            useJUnitPlatform() // 테스트시 JUnit이 동작하도록 설정
        }

        sonarqube {
            properties {
                property "sonar.host.url", "http://localhost:9000"
                property "sonar.login", "admin"
                property "sonar.password", "password"
            }
        }

        task testSonarqube {
            dependsOn(':test', ':jacocoTestReport', ':jacocoTestCoverageVerification', ':sonarqube')

            jacocoTestReport.mustRunAfter test
            jacocoTestCoverageVerification.mustRunAfter jacocoTestReport
            tasks.sonarqube.mustRunAfter jacocoTestCoverageVerification
        }    
        ```
        ``` bash
        gradle :testSonarqube
        ``` 
        - 로그인
        ``` text
        http://localhost:9000
        id: admin, pwd: admin
        ```

#### 2. 전통적인 JDBC 방식으로 개발 - DriverManager
##### JDBC(Java Database Connectivity)
- JDBC : 다양한 DB 서버를 자바에서 공통적으로 사용하기 위한 공통 스펙(API)
- DB 벤더별로 접속 라이브러리를 제공
##### DB: MariaDB
- DB 벤더별 JDBC 라이브러리를 추가. 여기서는 MariaDB용 JDBC 라이브러리를 추가함.
- DriverManager 이용 : DB 연결을 위한 기본적인 서비스
    > The basic service for managing a set of JDBC drivers.
- docker-compose.yml
    ``` yml
    version: "3"
    services:
    db:
        image: mariadb
        container_name: mariadb-study
        ports:
        - "3306:3306"
        environment:
        MARIADB_ROOT_PASSWORD: "1234"
        command:
        - --character-set-server=utf8mb4
        - --collation-server=utf8mb4_unicode_ci
        volumes:
        - ./datadir:/var/lib/mysql
        - .:/docker-entrypoint-initdb.d/    
    ```
- init.d.sql
    ``` sql
    CREATE USER 'sjkim'@'%' IDENTIFIED BY 'password';
    grant all privileges on *.* to sjkim@'%';
    flush privileges;
    create database school;
    use school;
    create table student (
        id bigint auto_increment,
        name varchar(255) null,
        age int null,
        birth date null,
        phone varchar(255) null,
        constraint student_pk primary key(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    ```
    ``` bash
    gradle :composeUp
    docker exec -it mariadb-study bash
    mysql -u sjkim -p
    ```
##### project spring-jdbc-example
- jdbc1 package
    - StudentService의 각 method마다 DB Driver 객체 정보를 얻고, DriverManager을 통한 DB 연결이 이루어짐
    - jdbc url, id, password의 반복 
    - Connection 연결과 close가 반복
``` java
public List<Student> getAll() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();

    String url = "jdbc:mariadb://localhost:3306/school";
    String user = "sjkim";
    String password = "password";
    Connection conn = DriverManager.getConnection(url, user, password);

    PreparedStatement psm = conn.prepareStatement("select * from student");
    ResultSet resultSet = psm.executeQuery();
    List<Student> students = new ArrayList<>();
    while (resultSet.next()) {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        student.setBirth(resultSet.getDate("birth").toLocalDate());
        students.add(student);
    }

    resultSet.close();
    psm.close();
    conn.close();
    return students;
}
```
- jdbc2 package
    - DBConn에 공통된 부분을 따로 분리하여 getConnection(), close() 구현
    - PreparedStatement close가 반복
    - Connection, PreparedStatement의 try-with-resources 미적용
``` java
public List<Student> getAll() {
    Connection conn = DBConn.getConnection();
    PreparedStatement psm = null;
    ResultSet resultSet = null;
    List<Student> students = new ArrayList<>();
    String sql = "select * from student";
    try {
        psm = conn.prepareStatement(sql);
        resultSet = psm.executeQuery();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setBirth(resultSet.getDate("birth").toLocalDate());
            students.add(student);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (psm != null) {
            try {
                psm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    DBConn.close();
    return students;
}
```
#### 3. Template Method Pattern 적용하여 개발
##### project spring-jdbc-tmplt-example
- DB
    - DB Connection을 연결해 줄 인터페이스를 만들어서 각자 사용하는 DB에 맞게 개발할 수 있음
    - 실무에서는 아래의 ConnectionFactory와 같은 인터페이스를 만들어서 직접 클래스를 구현하여 개발하는 것은 드물고 DB Connection을 가져오는 DataSource 인터페이스를 구현한 클래스를 사용
    - 또한 매번 DB와 연결하는 방식은 성능 문제로 인해 사용되지 않으며, 주로 대량의 데이터 처리를 하는 웹 애플리케이션에서는 미리 Connection을 연결하고 반환하는 Connection Pooling방식을 사용 (HikariCP 등)
    - 스프링에서는 세 가지 DataSource 설정을 지원
        - DriverManager 이용 → 성능 문제로 테스트 목적으로 이용됨
        - JNDI 이용
        - Connection Pool 이용 → 스프링에서 구현 클래스를 직접 제공하지 않으므로 라이브러리를 이용함
``` java
public interface ConnectionFactory {
    Connection getConnection();
    void close();
}
```
``` java
@Slf4j
public class MariadbConnection implements ConnectionFactory {

    private Connection conn;
    private final String driverClassName;
    private final String url;
    private final String user;
    private final String password;

    public MariadbConnection(String driverClassName, String url, String user, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(driverClassName);
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                log.info("======== connection start");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void close() {
        try {
            if (!conn.isClosed()) {
                conn.close();
                log.info("======== connection close");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = null;
    }
}
```


##### Template Method Pattern
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

##### SingerGroupDaoImpl Class
- BaseQueryProcessor 추상클래스 생성
- 각 method마다 아래와 같이 Template Method를 생성 (그래도 중복되는 코드가 있어서 수정 필요) 
- addModelSetPreparedStatement()는 추상 메소드로 BaseQueryProcessor를 상속한 클래스에서 재정의 
``` java
public boolean executeAddPreparedStatement(String sql, T model) throws Exception {
    Connection conn = connectionFactory.getConnection();
    PreparedStatement psm = conn.prepareStatement(sql);
    addModelSetPreparedStatement(psm, model);
    psm.execute();
    psm.close();
    connectionFactory.close();
    return true;
}
``` 
``` java
@Override
public void addModelSetPreparedStatement(PreparedStatement psm, SingerGroup model) throws Exception {
    psm.setString(1, model.getName());
    psm.setDate(2, Date.valueOf(model.getDebutDate()));
    psm.setString(3, model.getAgency());
}
```
##### Template/Callback Pattern
 

#### 4. JdbcTemplate 사용하여 개발
##### project spring-jdbc-tmplt2-example 
- HikariCP
    - Connection Pool 라이브러리
- JdbcTemplate
    - 스프링에서 제공하는 JDBC template class. 중복되는 코드를 제거할 수 있음.
``` groovy
    implementation ("org.springframework:spring-jdbc:${springVersion}")
    implementation ("com.zaxxer:HikariCP:5.0.0")
    implementation ("org.mariadb.jdbc:mariadb-java-client:2.7.3")
```    
``` java
@Configuration
public class JavaConfig {

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
//        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/school");
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mariadb://localhost:3306/school");
        hikariConfig.setUsername("sjkim");
        hikariConfig.setPassword("password");
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public StudentDaoImpl studentDaoImpl(JdbcTemplate jdbcTemplate) {
        return new StudentDaoImpl(jdbcTemplate);
    }
}
```
``` java
public class StudentDaoImpl implements CommonDao<Student> {

    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student get(long id) {
        String sql = "select * from student where id = ?";
        List<Student> students = jdbcTemplate.query(sql, (rs, rowNum) -> convertResultSetToStudent(rs), id);
        return students.isEmpty() ? null : students.get(0);
    }

    private Student convertResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setPhone(rs.getString("phone"));
        student.setBirth(rs.getDate("birth").toLocalDate());
        return student;
    }
}
```

---
> https://docs.gradle.org/current/userguide/jacoco_plugin.html 
https://plugins.gradle.org/plugin/org.sonarqube 
https://tomgregory.com/how-to-measure-code-coverage-using-sonarqube-and-jacoco/
https://kwonnam.pe.kr/wiki/gradle/sonarqube
https://woowabros.github.io/experience/2020/02/02/jacoco-config-on-gradle-project.html
Head First Design Pattern 
https://reactiveprogramming.io/books/patterns/img/patterns-articles/templete-method-diagram.png
토비의 스프링
코드로 배우는 스프링 웹 프로젝트 개정판
스프링 부트로 배우는 자바 웹 개발
Spring 4.0 프로그래밍