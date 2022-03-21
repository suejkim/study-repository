package com.sjkim.springbootjpa.controller;

import com.sjkim.springbootjpa.domain.PingRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class PingController {

    @GetMapping("/get")
    public String get(@RequestParam String name) {
        return "HELLO " + name;
    }

    @PostMapping("/post")
    public String post(@RequestBody PingRequest request) {
        System.out.println("message: " + request.getMessage());
        return request.getMessage();
    }
}
