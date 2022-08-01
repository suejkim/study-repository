package com.sjkim.springbootexample.service.di_autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XService {
    @Autowired
    private YService yService;
    @Autowired
    private ZService zService;

    public XService() {
        System.out.println(">> XService constructor");
    }
}