package com.sjkim.home;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {

    private String name;
    private int age;

    @Builder
    public PersonDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
