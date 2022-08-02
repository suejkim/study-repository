package com.sjkim.springbootexample.service.di_constructor;

import org.springframework.stereotype.Service;

//@Lazy // AService에서 DService 의존하므로 @Lazy 작동하지 않음
@Service
public class DService {
    public DService() {
        System.out.println("DService constructor");
    }
}
