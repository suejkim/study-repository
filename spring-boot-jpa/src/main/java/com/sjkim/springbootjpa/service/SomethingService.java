package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.aop.Timer;
import com.sjkim.springbootjpa.dto.PingRequest;
import org.springframework.stereotype.Service;

@Service
public class SomethingService {

    @Timer(message = "Timer Test")
    public String getSomething(int intValue, String stringValue, PingRequest request) throws InterruptedException {
        System.out.println("==============> start getSomething");
        Thread.sleep(2000);
        System.out.println("==============> end getSomething");
        return "Something";
    }

    public void occurException() {
        throw new RuntimeException("TestAdvisor Test 위한 예외 발생");
    }
}
