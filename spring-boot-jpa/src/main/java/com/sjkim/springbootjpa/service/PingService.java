package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.aop.Timer;
import com.sjkim.springbootjpa.dto.PingRequest;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    @Timer
    public String getSomething() throws InterruptedException {
        Thread.sleep(2000);
        return "Something";
    }

    public String postSomething(PingRequest request) {
        System.out.println("message: " + request.getMessage());
        return request.getMessage();
    }
}
