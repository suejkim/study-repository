package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.ActionType;
import com.sjkim.springbootjpa.domain.Gender;
import com.sjkim.springbootjpa.domain.User;
import com.sjkim.springbootjpa.domain.UserHistory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ListenerTest {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    private User buildUse2rRequest() {
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
    void prePersistAndPostPersistTest() {
        var user = buildUse2rRequest();
        userRepository.save(user);
        var userHistory = buildUserHistoryRequest(user);
        userHistoryRepository.save(userHistory); // save
    }

    @Test
    void otherEventTest() {
        // postLoad
        var findUser = userRepository.findById(2L).get();
        var findUserHistory = userHistoryRepository.findById(1L).get();
        findUserHistory.updateUser(findUser);
        userHistoryRepository.save(findUserHistory); // update

        userHistoryRepository.delete(findUserHistory); // remove
    }
}
