package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.dto.SomethingRequest;
import com.sjkim.springbootjpa.dto.UserInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SomethingServiceTest {

    @Autowired
    private SomethingService somethingService;

    @Test
    @SneakyThrows
    void getSomething() {
        var value = "MESSAGE";
        SomethingRequest request = new SomethingRequest(value);
        var result = somethingService.getSomething(10, "TEST", request);
        assertThat(result).isEqualTo(value);
    }

    @Test
    void occurException() {
        assertThrows(RuntimeException.class, () -> {
            somethingService.occurException();
        });
    }

    @Test
    void registerUser() {
        var name = Base64.getEncoder().encodeToString("러블리".getBytes(StandardCharsets.UTF_8));
        var email = Base64.getEncoder().encodeToString("abc@gmail.com".getBytes(StandardCharsets.UTF_8));
        UserInfo userInfo = new UserInfo(name, email);
        System.out.println("before method call userInfo : " + userInfo);
        var targetUserInfo = somethingService.registerUser(userInfo);
        System.out.println("after method call userInfo : " + targetUserInfo);
        assertThat(targetUserInfo).isEqualTo(userInfo);
    }
}