package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.dto.PingRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SomethingServiceTest {

    @Autowired
    private SomethingService somethingService;

    @Test
    @SneakyThrows
    void getSomething() {
        PingRequest request = new PingRequest("MESSAGE");
        somethingService.getSomething(10, "TEST", request);
    }

    @Test
    void occurException() {
        assertThrows(RuntimeException.class, () -> {
            somethingService.occurException();
        });
    }
}