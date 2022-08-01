package com.sjkim.springbootexample.service.di_constructor;

import org.springframework.stereotype.Service;

@Service
public class BService {
    private final CService cService;
    private final DService dService;

    public BService(CService cService, DService dService) {
        System.out.println("BService constructor");
        this.cService = cService;
        this.dService = dService;
    }
}
