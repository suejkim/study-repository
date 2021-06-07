Spring (작업중)
===
---
1. Framework
2. Spring Framework
3. 특징
---
#### 1. Framework
- 뼈대를 이루는 코드 묶음
- 프로그램의 기본 흐름과 구조를 미리 정하고, 개발 팀원이 이 구조에 코드를 추가하는 방식으로 개발 → 뼈대는 이미 만들어져 있으므로 빠르게 개발 가능

#### 2. Spring Framework
- 모듈을 선택해서 사용 → 경량 프레임워크
- 자바 엔터프라이즈 개발을 위한 오픈소스 애플리케이션 프레임워크 → **동적 웹 사이트 개발을 위한 프레임워크**
- 엔터프라이즈 개발이란? 대규모 데이터 처리가 필요하고 기업을 대상으로 하는 개발
- Spring이 나오기까지...
    - 엔터프라이즈 개발을 위해 기존에는 EJB(Enterprise Java Beans) 사용
    - EJB는 스펙인데, 이 스펙을 지키기 위해 비즈니스 로직과 더불어 EJB를 위한 코드가 증가
    - 이에 따라 특정 환경에 종속적이고 무거워져서 경량화된 프레임워크의 필요성을 느낌
    - 2003년쯤 로드 존슨이 'J2EE 설계와 개발(Expect One-on-One J2EE development without EJB)'이라는 책을 내면서 Spring이 시작
- **경량화된 프레임워크**
    - '경량화' → 각각 제공하는 별도의 모듈이 분리되어 있는데, 해당되는 모듈을 추가, 조립해서 원하는 기능을 개발할 수 있도록 함

#### 3. 특징
<img src="https://www.tutorialandexample.com/wp-content/uploads/2019/10/Spring-Modules.png
" width="50%" title="spring_modules" alt="spring_modules"></img>
- 제공하는 기능에 따라 별도의 모듈로 분리되어있음
- POJO(Plain Old Java Object)의 구성으로 제작 (Java Beans) 
    - 일반 Java Code를 이용해서 객체를 만드는 방식 그대로 사용할 수 있음
    - POJO는 Spring 이전의 무거운 객체를 만들어 사용한 것에 대한 반발로 만들어진 용어
    - Spring → POJO 기반의 프레임워크
    - 라이브러리나 기술에 종속적이지 않아 유연한 개발을 함
- DI(의존성 주입) 
    - 의존성은 하나의 객체가 다른 객체 상태에 영향을 받으며, 주입은 외부에서 밀어넣는다는 것을 의미
    - A객체에서 B객체를 생성하지 않고, 외부에 필요하다는 신호를 보내면 B객체를 외부에서 주입해주는 것
- AOP
    - 공통으로 사용하고 있는 기능을 분리하여 따로 관리(로깅, 보안, 트랜잭션 등)




---
> https://www.tutorialandexample.com/wp-content/uploads/2019/10/Spring-Modules.png
https://khj93.tistory.com/entry/Spring-Spring-Framework란-기본-개념-핵심-정리
https://gmlwjd9405.github.io/2018/10/26/spring-framework.html
https://laptrinhx.com/spring-framework-tutorial-442539211/
https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html
https://multifrontgarden.tistory.com/186
https://velog.io/@outstandingboy/Spring-%EC%99%9C-%EC%8A%A4%ED%94%84%EB%A7%81-%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C-Spring-vs-EJB-JavaEE
코드로 배우는 스프링 웹 프로젝트
Spring 4.0 프로그래밍
