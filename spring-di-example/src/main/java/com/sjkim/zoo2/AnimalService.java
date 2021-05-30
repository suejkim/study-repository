package com.sjkim.zoo2;

public class AnimalService {

    private Animal animal;
    public AnimalService(Animal animal) { // 외부에서 받아진 것을 사용. 의존 관계가 바뀜
        this.animal = animal;
    }

    public void eatAnimal() {
        animal.eat();
    }
    public void actAnimal() {
        animal.act();
    }
}
