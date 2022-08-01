package com.sjkim.springbootexample.service.di_constructor;

import org.springframework.stereotype.Service;

@Service
public class AService {
    private final DService dService;

    public AService(DService dService) {
        System.out.println("AService constructor");
        this.dService = dService;
    }
}

/*
생성자를 이용한 주입 Constructor Injection
DService -> AService -> CService -> BService 순으로 로드된다.
 */