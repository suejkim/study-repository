package com.sjkim.springbootjpa.spring_test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Source implements Serializable {
    private String name;
    private int age;
    private InnerSource innerSource;

    public Source(String name, int age, InnerSource innerSource) {
        this.name = name;
        this.age = age;
        this.innerSource = innerSource;
    }

    public Source changeName(String name) {
        this.name = name;
        return this;
    }
}
