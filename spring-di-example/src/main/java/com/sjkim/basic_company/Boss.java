package com.sjkim.basic_company;

public class Boss implements Worker {
    @Override
    public String getRole() {
        return "보스";
    }
}
