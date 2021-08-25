package com.sjkim.security;

import com.sjkim.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CustomUserDetailsServiceTest extends BaseTest {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserByUsername() {
        String loginId = "admin01";
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginId);
        Matchers.equalTo(userDetails.getUsername()).matches(loginId);
    }
}