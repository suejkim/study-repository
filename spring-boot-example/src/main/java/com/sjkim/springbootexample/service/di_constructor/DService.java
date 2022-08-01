package com.sjkim.springbootexample.service.di_constructor;

import org.springframework.stereotype.Service;

@Service
public class DService {
    public DService() {
        System.out.println("DService constructor");
    }
}
