package com.sjkim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.sjkim.user",
        "com.sjkim.web"
})
@EntityScan(basePackages = {
        "com.sjkim.user.domain"
})
@EnableJpaRepositories(basePackages = {
        "com.sjkim.user.repository"
})
public class UserDetailsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDetailsTestApplication.class, args);
    }

}
