package com.sjkim.config_company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Worker boss() {
        return new Boss();
    }

    @Bean
    public Worker employee() {
        return new Employee();
    }

    @Bean
    public WorkService workService() {
        WorkService workService = new WorkService();
        workService.setWorkManager(new Boss()); // 여기만 수정하면 됨
        return workService;
    }
}
