package com.sjkim.springbootjpa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(
                name = "MOBILE_UNIQUE",
                columnNames = {"mobile"}
        )})
//@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Wrapper Type

    @Column(name = "login_id", nullable = false, length = 30)
    private String loginId;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false, length = 5)
    private Gender gender;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;

//    @Builder
//    public User(String loginId, String password, String name, String mobile, Gender gender) {
//        this.loginId = loginId;
//        this.password = password;
//        this.name = name;
//        this.mobile = mobile;
//        this.gender = gender;
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = new Date();
//    }
}
