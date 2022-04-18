package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.aop.Decoder;
import com.sjkim.springbootjpa.aop.Timer;
import com.sjkim.springbootjpa.dto.SomethingRequest;
import com.sjkim.springbootjpa.dto.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class SomethingService {

    @Timer(message = "Timer Test")
    public String getSomething(int intValue, String stringValue, SomethingRequest request) throws InterruptedException {
        System.out.println("==============> start getSomething");
        Thread.sleep(2000);
        System.out.println("==============> end getSomething");
        return request.getMessage();
    }

    public void occurException() {
        throw new RuntimeException("TestAdvisor Test 위한 예외 발생");
    }

    @Decoder
    public UserInfo registerUser(UserInfo userInfo) {
        // register process ...
        System.out.println(userInfo + " in method");
        return userInfo;
    }
}
