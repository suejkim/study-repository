package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.ActionType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class NPlusOneIssueTest {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void userHistoryNPlusOneTestForEager() {
        log.info("============================> query");
        var userHistories = userHistoryRepository.findByType(ActionType.SELL);
        log.info("<============================ query");
        userHistories.forEach(userHistory -> {
            log.info("user History {} ", userHistory);
            var user = userHistory.getUser();
            log.info("user {}", user.getName());
        });
    }

    @Test
    @Transactional
    void userHistoryNPlusOneTestForLazy() {
        log.info("============================> query");
        var userHistories = userHistoryRepository.findByType(ActionType.SELL);
        log.info("<============================ query");
        userHistories.forEach(userHistory -> {
            log.info("user History {} ", userHistory);
            var user = userHistory.getUser();
            log.info("============================> LAZY");
            log.info("user {}", user.getName());
            log.info("<============================ LAZY");
        });
    }
}
