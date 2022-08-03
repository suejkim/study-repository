# Spring Data JPA_2

---

1. 영속성 전이
2. 고아객체
3. 리스너

##### `spring-boot-jpa` project 참고

---

### 1.

### 2.

### 3. 리스너
#### 정의
테이블에 공통적으로 들어가는 컬럼(`created_at`, `updated_at` 등)일 경우,
누락되기 쉬운 데이터일 경우 (Setter),
Audit의 기능으로서 Entity 저장/수정의 로그성 데이터가 필요한 경우
이벤트를 관찰하다가 이벤트가 발생하면 자동으로 지정된 동작을 수행한다.

#### 이벤트 종류
- `PrePersist, PostPersist`: Entity가 영속성 컨텍스트에 관리되기 전/후 호출
- `PreUpdate, PostUpdate`: commit 후 데이터가 DB에 수정되기 전/후 호출
- `PreRemove, PostRemove`: DB에 삭제되기 전/후 호출
- `PostLoad`: Entity가 영속성 컨텍스트에 조회된 직후 호출

#### 리스너 등록
##### Entity 내 이벤트 어노테이션 직접 적용

```java
@Slf4j
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        log.info(">> prePersist");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        log.info(">> preUpdate");
        this.updatedAt = new Date();
    }

    @PostLoad
    private void postLoad() {
        log.info(">> postLoad");
    }

    @PreRemove
    private void preRemove() {
        log.info(">> preRemove");
    }

    @PostPersist
    private void postPersist() {
        log.info(">> postPersist");
    }

    @PostUpdate
    private void postUpdate() {
        log.info(">> postUpdate");
    }

    @PostRemove
    private void postRemove() {
        log.info(">> postRemove");
    }
}
```
- `@MappedSuperclass`: 공통으로 사용되는 매핑정보들이 모인 클래스
- 공통 매핑정보만 지정하여 필요한 Entity 리스너 등록 후 상속 받아 사용

##### 커스텀 리스너
1. 공통 컬럼 적용시
```java
public interface Auditable {
    LocalDateTime getCreatedAt();
    Date getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(Date updatedAt);
}
```
```java
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@EntityListeners(value = BaseEntityForEntityListeners.class) // 커스텀 리스너 등록
public class Author implements Auditable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 생략

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

    // builder 생략
}
```
```java
public class BaseEntityForEntityListeners {
    @PrePersist
    private void prePersist(Object obj) {
        if (obj instanceof Auditable) {
            ((Auditable) obj).setCreatedAt(LocalDateTime.now());
            ((Auditable) obj).setUpdatedAt(new Date());
        }
    }

    @PreUpdate
    private void preUpdate(Object obj) {
        if (obj instanceof Auditable) {
            ((Auditable) obj).setUpdatedAt(new Date());
        }
    }
}
```
- `@EntityListeners`: 리스너 클래스를 지정
- Object 타입의 파라미터가 필요하다. 지정된 Entity로 객체 타입을 확인하는 것보다 Auditable 인터페이스를 사용하여 다형성 시도

2. Audit 기능
```java
@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
@EntityListeners(UserEntityListener.class) // 커스텀 리스너 지정
public class User extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // field, builder 생략

}
```
```java
@Getter
@Entity
@Table
@NoArgsConstructor
public class UserUpdateLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // field, builder 생략
}
```
```java
@Component
public class UserEntityListener {

    private UserUpdateLogRepository userUpdateLogRepository;

    public UserEntityListener() {

    }

    public UserEntityListener(@Lazy UserUpdateLogRepository userUpdateLogRepository) {
        this.userUpdateLogRepository = userUpdateLogRepository;
    }

    @PostPersist
    @PostUpdate
    private void postPersistAndUpdate(Object obj) {
        User user = (User) obj;
        UserUpdateLog userUpdateLog = UserUpdateLog.builder()
                .userId(user.getId())
                .name(user.getName())
                .gender(user.getGender())
                .mobile(user.getMobile())
                .build();
        userUpdateLogRepository.save(userUpdateLog);
    }
}
```
- `User → UserUpdateLog` User 정보가 저장/수정될 경우 UserUpdateLog에도 저장될 수 있도록 동작
- `@Lazy`: Spring은 Application Context 객체 생성 후 Bean이 등록되는데 그 시점이 아닌 실제 객체 사용 시점에 등록되도록 한다.
```java
@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
```
- 또는 `BeanUtils.getBean();` method로 ApplicationContext에 있는 Bean을 리턴받아서 사용할 수 있다.

(3) Spring에서 제공하는 리스너
```java
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Author extends BaseEntityForAuditing {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // field, builder 생략
}
```
```java
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class) // Spring에서 제공
public class BaseEntityForAuditing {

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

}
```
- Config class에 `@EnableJpaAuditing` 추가: Spring이 제공하는 Auditing 기능을 사용할 수 있게 해준다.
- `AuditingEntityListener`: Entity 리스너로써 Entity Auditing 역할을 수행
- `@CreatedDate`, `@LastModifiedDate`: 생성날짜 및 마지막 수정날짜를 자동으로 설정해주기 위한 어노테이션
---
> https://www.baeldung.com/database-auditing-jpa 
> https://www.baeldung.com/spring-lazy-annotation 
> https://antop.tistory.com/entry/Spring-Lazy 
> https://www.baeldung.com/circular-dependencies-in-spring
> 자바 ORM 표준 JPA 프로그래밍
> 스프링부트로 배우는 자바웹개발