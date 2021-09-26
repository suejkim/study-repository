package com.sjkim.springbootexample.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id")
    private String loginId;
    private String password;
    private String username;

    @Builder
    public Member(Long id, String loginId, String password, String username) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.username = username;
    }
}
