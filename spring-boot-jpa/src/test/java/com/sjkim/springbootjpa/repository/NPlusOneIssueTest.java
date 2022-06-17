package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.ActionType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
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
            log.info("user {}", user);
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
            log.info("user {}", user);
            log.info("<============================ LAZY");
        });
    } // LAZY, EAGER 상관없이 history (1) 조회 후 user수 (N) 만큼 쿼리 실행

    @Test
    void userNPlusOneTestForEager() {
        log.info("============================> query");
        var users = userRepository.findByName("NAME1");
        log.info("<============================ query");
        users.forEach(user -> {
            var userHistories = user.getUserHistories();
            log.info("{}", userHistories);
        });
    }

    @Test
    @Transactional
    void userNPlusOneTestForLazy() {
        log.info("============================> query");
        var users = userRepository.findByName("NAME1");
        log.info("<============================ query");
        users.forEach(user -> {
            var userHistories = user.getUserHistories();
            log.info("============================> LAZY");
            log.info("{}", userHistories);
            log.info("<============================ LAZY");
        });
    }

    @Test
    @DisplayName("N+1 이슈 해결방법 1")
    void findByUserHistoryWithFetchJoin() {
        log.info("============================> query");
        var userHistories = userHistoryRepository.findByUserHistoryWithFetchJoin();
        userHistories.forEach(history -> {
            log.info("history {}", history.getId());
        });
        log.info("<============================ query");
        userHistories.forEach(userHistory -> {
            log.info("user History {} ", userHistory);
            var user = userHistory.getUser();
            log.info("user {}", user);
        });
    } // UserHistoy N : User 1  => distinct 필요없음

    @Test
    @DisplayName("N+1 이슈 해결방법 1")
    void findByDistinctUserWithFetchJoin() {
        log.info("============================> query");
        var users = userRepository.findByDistinctUserWithFetchJoin();
        users.forEach(user -> {
            log.info("user {}", user.getId()); // distinct 적용 확인
        });
        log.info("<============================ query");
        users.forEach(user -> {
            var userHistories = user.getUserHistories();
            log.info("{}", userHistories);
        });
    } // User 1 : UserHistoy N => distinct 중복 제거 필요

    @Test
    @DisplayName("N+1 이슈 해결방법 2")
    void findAllUserHistoryWithEntityGraph() {
        log.info("============================> query");
        var userHistories = userHistoryRepository.findAll();
        userHistories.forEach(history -> {
            log.info("history {}", history.getId());
        });
        log.info("<============================ query");
        userHistories.forEach(userHistory -> {
            log.info("user History {} ", userHistory);
            var user = userHistory.getUser();
            log.info("user {}", user);
        });
    }

    @Test
    @DisplayName("N+1 이슈 해결방법 2")
    void findAllUserWithEntityGraph() {
        log.info("============================> query");
        var users = userRepository.findAll(); // user 중복 제거 적용됨
        users.forEach(user -> {
            log.info("user {}", user.getId());
        });
        log.info("<============================ query");
        users.forEach(user -> {
            var userHistories = user.getUserHistories();
            log.info("{}", userHistories);
        });
    }
}
