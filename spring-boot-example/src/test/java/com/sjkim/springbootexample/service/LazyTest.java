package com.sjkim.springbootexample.service;

import com.sjkim.springbootexample.service.di_constructor.AService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class LazyTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void lazyTest() {
        var aService = applicationContext.getBean(AService.class);
    }
}
