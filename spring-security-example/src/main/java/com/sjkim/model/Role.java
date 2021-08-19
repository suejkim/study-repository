package com.sjkim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "role")
@NoArgsConstructor
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

    @Builder
    public Role(Long memberId, Member member, MemberRole memberRole) {
        this.memberId = memberId;
        this.member = member;
        this.memberRole = memberRole;
    }
}
