package com.sjkim.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Role {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "member_id")
    private Member member;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;
}
