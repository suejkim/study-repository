package com.sjkim.zoo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AnimalService {

    @Autowired
    @Qualifier("monkey")
    private Animal animal;

    public void eatAnimal() {
        animal.eat();
    }
    public void actAnimal() {
        animal.act();
    }
}
