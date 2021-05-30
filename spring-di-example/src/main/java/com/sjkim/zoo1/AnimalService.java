package com.sjkim.zoo1;

public class AnimalService {

    private Monkey monkey;
    public AnimalService (){
        this.monkey = new Monkey(); // AnimalService가 Monkey에 의존. 직접 만들어서 사용
    }

    public void eatAnimal() {
        monkey.eat();
    }
    public void actAnimal() {
        monkey.act();
    }
}
