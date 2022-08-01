package com.sjkim.springbootexample.service.di_constructor;

import org.springframework.stereotype.Service;

@Service
public class CService {
    public CService() {
        System.out.println("CService constructor");
    }
}
