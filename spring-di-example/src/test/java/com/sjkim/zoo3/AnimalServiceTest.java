package com.sjkim.zoo3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class})
public class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @Test
    public void actTest() {
        animalService.actAnimal();
    }

    @Test
    public void eatTest() {

    }
}
