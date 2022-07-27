package com.sjkim.springbootjpa.domain;

import com.sjkim.springbootjpa.repository.UserUpdateLogRepository;
import com.sjkim.springbootjpa.support.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

//@Component
public class UserEntityListener {

//    private UserUpdateLogRepository userUpdateLogRepository;
//
//    public UserEntityListener() {
//
//    }
//
//    public UserEntityListener(@Lazy UserUpdateLogRepository userUpdateLogRepository) {
//        this.userUpdateLogRepository = userUpdateLogRepository;
//    }

    @PostPersist
    @PostUpdate
    private void postPersistAndUpdate(Object obj) {
        var userUpdateLogRepository = BeanUtils.getBean(UserUpdateLogRepository.class);
        User user = (User) obj;
        UserUpdateLog userUpdateLog = UserUpdateLog.builder()
                .userId(user.getId())
                .name(user.getName())
                .gender(user.getGender())
                .mobile(user.getMobile())
                .build();
        userUpdateLogRepository.save(userUpdateLog);
    }
}