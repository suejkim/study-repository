package com.sjkim.springbootjpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false, length = 5)
    private Gender gender;

    @Builder
    public UserUpdateLog(Long userId, String name, String mobile, Gender gender) {
        this.userId = userId;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
    }
}
