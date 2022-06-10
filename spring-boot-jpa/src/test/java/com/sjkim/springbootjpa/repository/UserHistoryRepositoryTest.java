package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.ActionType;
import com.sjkim.springbootjpa.domain.Gender;
import com.sjkim.springbootjpa.domain.User;
import com.sjkim.springbootjpa.domain.UserHistory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class UserHistoryRepositoryTest {

    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void addUserHistory() {
        var user = buildUserRequest();
        userRepository.save(user);
        var userHistory = buildUserHistoryRequest(user);
        userHistoryRepository.save(userHistory);
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

    private UserHistory buildUserHistoryRequest(User user) {
        return UserHistory.builder()
                .user(user)
                .type(ActionType.BUY)
                .build();
    }

    @Test
    @DisplayName("N+1 test")
    @Transactional // Fetch Type LAZY 설정 때문
    void findByUserHistory() {
        var userHistories = userHistoryRepository.findAll();
        userHistories.forEach(userHistory -> {
            log.info("user History {}", userHistory.getType());
            var user = userHistory.getUser();
            log.info("user {}", user.getName());
        });
    } // LAZY, EAGER 상관없이 history (1) 조회 후 user수 (N) 만큼 쿼리 실행
}
