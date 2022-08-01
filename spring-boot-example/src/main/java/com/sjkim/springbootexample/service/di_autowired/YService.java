package com.sjkim.springbootexample.service.di_autowired;

import org.springframework.stereotype.Service;

@Service
public class YService {
    public YService() {
        System.out.println(">> YService constructor");
    }
}
