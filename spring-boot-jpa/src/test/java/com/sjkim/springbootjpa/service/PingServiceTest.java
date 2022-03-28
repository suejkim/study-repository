package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.dto.PingRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PingServiceTest {

    @Autowired
    private PingService pingService;

    @Test
    @SneakyThrows
    void getSomething() {
        pingService.getSomething();
    }

    @Test
    void postSomething() {
        PingRequest request = new PingRequest("MESSAGE");
        pingService.postSomething(request);
    }
}