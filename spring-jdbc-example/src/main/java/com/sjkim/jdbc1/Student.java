package com.sjkim.jdbc1;

import java.time.LocalDate;

public class Student {

    private long id;
    private String name;
    private int age;
    private LocalDate birth;
    private String phone;

    public Student() {

    }

    public Student(String name, int age, LocalDate birth, String phone) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
