package com.sjkim.springbootjpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(
                name = "MOBILE_UNIQUE",
                columnNames = {"mobile"}
        ),
        @UniqueConstraint(
                name = "LOGIN_ID_UNIQUE",
                columnNames = {"login_id"}
        )})
@NoArgsConstructor
public class User extends BaseEntity {

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

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = UserHistory.class,
            mappedBy = "user"
    )
//    @JoinColumn(name = "user_history_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "FK_USER_HISTORY", value = ConstraintMode.PROVIDER_DEFAULT)
//    ) // Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn
    private List<UserHistory> userHistories;

    @Builder
    public User(String loginId, String password, String name, String mobile, Gender gender) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
    }
}
