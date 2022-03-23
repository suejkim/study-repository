package com.sjkim.springbootjpa.service;

import com.sjkim.springbootjpa.aop.MyAnnotation;
import org.junit.jupiter.api.Test;

class MyAnnotationServiceTest {

    @Test
    void printSomething() throws ClassNotFoundException {
        var methods = Class.forName(MyAnnotationService.class.getName()).getMethods();
        for (var method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                var annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("message: " + annotation.message());
                System.out.println("intValue: " + annotation.intValue());
            }
        }
    }
}