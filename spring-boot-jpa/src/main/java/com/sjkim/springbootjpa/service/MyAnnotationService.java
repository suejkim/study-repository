package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.aop.MyAnnotation;
import org.springframework.stereotype.Service;

@Service
public class MyAnnotationService {

    @MyAnnotation(message = "Message!", intValue = 1234)
    public void printSomething() {
        System.out.println("========== print something");
    }
}
