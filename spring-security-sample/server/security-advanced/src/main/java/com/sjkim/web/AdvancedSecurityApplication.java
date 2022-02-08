package com.sjkim.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.sjkim.web"
})
public class AdvancedSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedSecurityApplication.class, args);
    }

}
