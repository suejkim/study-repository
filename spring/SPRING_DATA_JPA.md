# Spring Data JPA
---
1. ORM(Object Relational Mapping)
2. JPA(Java Persistence API)
3. Spring Data JPA
##### `spring-boot-jpa` project 참고
---


### 1. ORM(Object Relational Mapping)
사용자들에게 지속적으로 서비스를 제공하기 위해서는 영구적인 데이터 관리가 필요하다. RDBMS로 Oracle, Mysql, Postgre 등 다양한 제품들이 있는데 이를 공통적으로 사용할 수 있도록 자바에서는 JDBC라는 스펙을 제공한다. 자바 애플리케이션은 JDBC API를 이용해서 SQL을 데이터베이스에 전달하고 결과를 반환 받는다. 
JDBC로도 개발할 수 있지만 비즈니스 로직이 자바 코드 보다는 Query에 집중되고, 자바를 Query 매핑하는 용도로 많이 사용하게 된다. 비즈니스 로직이 복잡해지면서 SQL Mapper(Mybatis, JdbcTemplate)를 사용하였지만 자바의 객체랑 구조화된 데이터 사이 간에 불일치가 발생하였다. 
이 불일치를 해결하기 위해 ORM이 등장하였는데, 자바에서 무엇보다 중요한 객체와 데이터베이스 사이의 관계를 연결(매핑)해주는 것으로, 객체 중심 설계에 집중할 수 있다.

### 2. JPA (Java Persistence API)
엔터프라이즈 개발을 위해 과거에 EJB(Enterprise Java Beans)라는 기술을 사용했는데, 거기엔 엔티티빈이라는 ORM 기술이 포함 되어있었지만 복잡하여 사용빈도가 낮았다. 이 때, Hibernate라는 ORM 프레임워크가 등장했는데 EJB의 엔티티빈보다 호응이 좋았는지 EJB 3.0에서 Hibernate 기반으로 새로운 자바 ORM 기술을 만들었는데 그것이 JPA이다. 
'자바 진영의 ORM 기술 표준으로 인터페이스를 모아둔 것'으로 정의할 수 있는데, 쉽게 표현하자면 자바에서 사용하는 ORM 기술이라고 생각하면 된다. 
개발자가 직접 쿼리문을 작성하지 않아도 자동으로 쿼리문을 만들어 데이터베이스에 전달하고, 그 결과를 반환받을 때, 자동으로 데이터를 객체에 맞게 매핑을 해준다. 
JPA는 데이터에 접근하기 위한 API라 인터페이스로 되어있고 JPA의 구현체로는 Hibernate, EclipseLink, DataNucleus가 있다. 스프링에서는 Spring Data JPA로 제공한다.
JPA는 자바 애플리케이션과 JDBC 사이에서 동작한다. (그림추가)

### 3. Spring Data JPA
스프링에서 Hibernate를 간편하게 사용할 수 있게 묶어서 제공하는 것이 Spring Data JPA이다. 즉, EntityManager를 직접적으로 사용하지 않고 데이터 접근이 쉽도록 CRUD의 공통 인터페이스를 제공한다.

즉, 
1. 애플리케이션과 데이터베이스를 연결하는 건 ORM
2. 자바에서 ORM 인터페이스를 제공하는 건 JPA
3. JPA의 구현 클래스를 모아둔 것이 Hibernate인데 그 중에 CRUD와 같이 사용빈도가 높고 자주 사용하는 것들을 스프링에서 제공하는 건 Spring Data JPA


#### Spring Data JPA의 Repository 구조



---
> 자바 ORM 표준 JPA 프로그래밍


 