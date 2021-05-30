package com.sjkim.zoo2;

public class Zoo {
    public static void main(String[] args) {

        // Monkey
        Monkey monkey = new Monkey();
        AnimalService monkeyService = new AnimalService(monkey); // 실행부분에서는 아직 직접 생생하여 주입함

        // Rabbit
        Rabbit rabbit = new Rabbit();
        AnimalService rabbitService = new AnimalService(rabbit);

        monkeyService.actAnimal();
        rabbitService.actAnimal();
    }
}
