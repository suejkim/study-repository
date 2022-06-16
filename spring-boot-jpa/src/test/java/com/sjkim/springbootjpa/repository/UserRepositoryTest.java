package com.sjkim.springbootjpa.repository;


import com.sjkim.springbootjpa.domain.Gender;
import com.sjkim.springbootjpa.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        var user = userRepository.save(buildUserRequest());
        var users = userRepository.findAll();
        log.info("{}", Lists.newArrayList(users).size());
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

    @Test
    void findByUserForFetchTypeEager() {
        var userOptional = userRepository.findById(1L);
        userOptional.ifPresent(user -> {
            log.info("user {}", user.getName());
            var userHistories = user.getUserHistories();
            log.info("user Histories {} ", userHistories);
        });
        // fetchType EAGER
        // -> left outer join
    }

    @Test
    @Transactional
    void findByUserForFetchTypeLazy() {
        var userOptional = userRepository.findById(1L);
        userOptional.ifPresent(user -> {
            log.info("user {}", user.getName());
            var orders = user.getOrders();
            log.info("user Histories {} ", orders);
        });
    }
}
