package com.sjkim.zoo3;

import org.springframework.stereotype.Component;

@Component // bean 등록
public class Rabbit implements Animal {

    @Override
    public void eat() {
        System.out.println("당근을 먹습니다.");
    }

    @Override
    public void act() {
        System.out.println("뛰어다닙니다.");
    }
}
