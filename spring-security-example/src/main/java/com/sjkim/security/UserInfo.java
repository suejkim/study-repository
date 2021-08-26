package com.sjkim.security;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfo {
    private String loginId;

    @Builder
    public UserInfo(String loginId) {
        this.loginId = loginId;
    }
}
