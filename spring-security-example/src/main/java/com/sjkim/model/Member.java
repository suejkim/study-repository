package com.sjkim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "loginid", unique = true)
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeCreated() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Member(Long id, String loginId, String password, String email, boolean enabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Member applyPassword(String newPassword) {
        this.password = newPassword;
        return this;
    }
}
