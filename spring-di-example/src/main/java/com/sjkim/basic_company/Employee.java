package com.sjkim.basic_company;

public class Employee implements Worker {
    @Override
    public String getRole() {
        return "직원";
    }
}
