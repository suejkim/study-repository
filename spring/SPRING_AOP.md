# Spring AOP

---

1. AOP(Aspect Oriented Programming)
2. 용어
3. Spring의 AOP
4. Advice 종류

##### `spring-boot-jpa` project 참고

---

### 1. AOP(Aspect Oriented Programming)

<img src="https://programmer.group/images/article/875539ff50c2d9085fe5473573c9316d.jpg" title="cross-cutting concern" alt="aop"></img>

XxxService처럼 각각의 비즈니스 로직마다 공통적인 기능이 요구되는 경우가 있다. 로깅, 권한, 캐싱, 공통 예외처리, 메서드 실행시간 체크, 메서드 파라미터 validate check, 보안, 트랜젝션 등. 애플리케이션 전반적으로 필요한 경우가 대부분이다. 하지만 공통 기능을 해당 로직에 계속 추가하는 건 중복 코드를 양산할 수 있는데 이를 해결하기 위해 AOP가 나타났다.
위의 공통적 기능들은 핵심 비즈니스 로직은 아니지만 '공통 관심사 cross-cutting concern'으로 분리하여 작성할 수 있다. 또는 위 그림처럼 횡으로 분리하였다고 하여 횡단 관심사라고도 한다.
즉, 공통 관심사를 모듈로 분리하는 것이 AOP이다. AOP 장점으로 아래와 같다.

1. 중복 코드 제거
2. 공통 관심사를 분리하여 개발자가 핵심 비즈니스 로직에만 집중
3. 공통 관심사의 유지보수 수월

### 2. 용어

| 용어      | 설명                                                                                        |
| --------- | ------------------------------------------------------------------------------------------- |
| Aspect    | 공통 관심사                                                                                 |
| Advice    | Aspect를 구현한 것으로 공통 관심사를 언제 적용할지 정의함. 공통 관심사를 가지고 있는 클래스 |
| Target    | 핵심 로직을 가진 객체                                                                       |
| Joinpoint | Target 내 메서드에 Advice를 적용할 수 있는 지점                                             |
| Pointcut  | 핵심 로직과 공통 관심사가 결합되는 지점을 결정                                              |
| Weaving   | Advice를 핵심 로직에 적용하는 것                                                            |

### 3. Spring의 AOP

핵심 코드에서 공통 관심사(공통 모듈)을 직접적으로 호출하지 않으며, 핵심 비즈니스 로직(개발자의 코드)와 공통 관심사를 구현한 코드를 컴파일 시점 또는 실행 시점에 결합시킨다. 핵심 비즈니스 로직을 감싸는 것을 Proxy라고 하며 Proxy는 자동으로 생성되는 auto-proxy 방식을 이용한다. Proxy를 이용하므로 메서드 호출에서만 AOP를 사용할 수 있다.

### 4. Advice 종류

| 종류                   | 설명                                                                               |
| ---------------------- | ---------------------------------------------------------------------------------- |
| Before Advice          | Target의 메서드 실행 전에 공통 기능 실행                                           |
| After Advice           | Target의 메서드 실행 도중 예외가 발생해도 공통 기능 실행                           |
| After Returning Advice | Target의 메서드 예외 발생없이 실행 이후 공통 기능 실행                             |
| After Throwing Advice  | Target의 메서드 실행 도중 예외가 발생한 경우 공통 기능 실행                        |
| Around Advice          | Target의 메서드 실행 전, 후, 예외 발생 시 공통 기능 실행 → 메서드 실행 제어가 가능 |

---

> https://programmer.group/simple-spring-aop-steps.html
