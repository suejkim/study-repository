package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.ActionType;
import com.sjkim.springbootjpa.domain.Gender;
import com.sjkim.springbootjpa.domain.User;
import com.sjkim.springbootjpa.domain.UserHistory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class UserHistoryRepositoryTest {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

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
    void findByUserHistoryForFetchTypeEager() {
        var userHistoryOptional = userHistoryRepository.findById(1L);
        userHistoryOptional.ifPresent(userHistory -> {
            log.info("user History {} ", userHistory);
            log.info("user History type {}", userHistory.getType());
            var user = userHistory.getUser();
            log.info("user {}", user.getName());
        });
        // fetchType EAGER
        // optional = false
        // -> innerJoin
    }

    @Test
    @Rollback(value = false)
    @Transactional
    void saveUserHistoryWhenUpdatableFalse() {
        var user = buildUserRequest();
        userRepository.save(user);
        var userHistory = UserHistory.builder().user(user).type(ActionType.SELL).build();
        userHistory = userHistoryRepository.save(userHistory);
        log.info("select query");
        var updateUser = userRepository.findById(1L).get();
        log.info("update query");
        userHistory = userHistory.updateUserAndActionType(updateUser, ActionType.BUY);
        assertThat(userHistory.getUser()).isEqualTo(updateUser);
        assertThat(userHistory.getType()).isEqualTo(ActionType.BUY);
        // type은 updatable = false로 적용되어 update에 포함되지 않는다.
        // 캐시때문에 test에서는 BUY로 확인되지만 DB에는 SELL로 저장됨 -> 영속성 컨텍스트 불일치 이슈
    }
}
