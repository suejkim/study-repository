package com.sjkim.springbootexample.service.di_autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WService {
    @Autowired
    private ZService zService;

    public WService() {
        System.out.println(">> WService constructor");
    }
}

/*
Field Injection
WService -> ZService -> XService -> YService 순으로 로드된다.
 */
