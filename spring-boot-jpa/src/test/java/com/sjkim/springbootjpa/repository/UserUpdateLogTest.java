package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.Gender;
import com.sjkim.springbootjpa.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class UserUpdateLogTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUpdateLogRepository userUpdateLogRepository;

    @Test
    void addUser() {
        var user = userRepository.save(buildUserRequest());
        user.updateName("UPDATE_NAME");
        userRepository.save(user);
        var userUpdateLog = userUpdateLogRepository.findByUserIdOrderByIdDesc(user.getId()).get(0);
        assertThat(user.getId()).isEqualTo(userUpdateLog.getUserId());
        assertThat(user.getName()).isEqualTo(userUpdateLog.getName());
        assertThat(user.getMobile()).isEqualTo(userUpdateLog.getMobile());
    }

    private User buildUserRequest() {
        return User.builder()
                .loginId("TEST2")
                .password("test2")
                .gender(Gender.MALE)
                .mobile("01011113333")
                .name("NAME")
                .build();
    }
}
