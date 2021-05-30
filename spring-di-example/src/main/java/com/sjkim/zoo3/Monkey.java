package com.sjkim.zoo3;

import org.springframework.stereotype.Component;

@Component // bean 등록
public class Monkey implements Animal {

    @Override
    public void eat() {
        System.out.println("바나나를 먹습니다.");
    }

    @Override
    public void act() {
        System.out.println("나무를 타면서 놉니다.");
    }
}
