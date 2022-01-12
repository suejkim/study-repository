package com.sjkim.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.sjkim.config",
        "com.sjkim.web"
})
public class RememberMeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RememberMeApplication.class, args);
    }
}
