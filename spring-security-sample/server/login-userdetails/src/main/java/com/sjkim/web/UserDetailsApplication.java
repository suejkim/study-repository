package com.sjkim.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = {
//        "com.sjkim.user",
//        "com.sjkim.web"
//})
//@EntityScan(basePackages = {
//        "com.sjkim.user.domain"
//})
//@EnableJpaRepositories(basePackages = {
//        "com.sjkim.user.repository"
//})

// 위의 설정과 동일함. config(UserAdminModule)에 몰아넣기
@SpringBootApplication(scanBasePackages = {
        "com.sjkim.config",
        "com.sjkim.web"
})
public class UserDetailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDetailsApplication.class, args);
    }

}
