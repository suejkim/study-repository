package com.sjkim.springbootexample.service.di_autowired;

import org.springframework.stereotype.Service;

@Service
public class ZService {
    public ZService() {
        System.out.println(">> ZService constructor");
    }
}
