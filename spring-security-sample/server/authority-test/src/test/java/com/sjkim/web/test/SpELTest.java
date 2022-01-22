package com.sjkim.web.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.assertj.core.api.Assertions.assertThat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Person {
    private String name;
    private int height;

    public boolean over(int pivot) {
        return height >= pivot;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Cat {
    private String name;
    private int height;

    public boolean over(int pivot) {
        return height >= pivot;
    }
}

class SpELTest {

    private final ExpressionParser parser = new SpelExpressionParser();
    private static final String NAME = "KIM";
    private final Person person = Person.builder().name(NAME).height(160).build();
    private final Cat cat = Cat.builder().name("navi").height(30).build();

    @Test
    void getValueTest() {
        assertThat(parser.parseExpression("name").getValue(person, String.class)).isEqualTo(NAME);
    }

    @Test
    void setValueTest() {
        var convertedName = "LEE";
        parser.parseExpression("name").setValue(person, convertedName);
        assertThat(parser.parseExpression("name").getValue(person)).isEqualTo(convertedName);
    }

    @Test
    void callMethodTest() {
        Assertions.assertTrue(parser.parseExpression("over(155)").getValue(person, Boolean.class));
    }

    // Bean Test
    @Test
    void contextTest() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        // resolver -> Bean 찾기
        context.setBeanResolver(new BeanResolver() {
            @Override
            public Object resolve(EvaluationContext context, String beanName) throws AccessException {
                return beanName.equals("person") ? person : cat;
            }
        });

        // bean은 @로 찾을 수 있음
        Assertions.assertTrue(parser.parseExpression("@person.over(155)").getValue(context, Boolean.class));
        Assertions.assertFalse(parser.parseExpression("@cat.over(100)").getValue(context, Boolean.class));

        context.setRootObject(person); // setRootObject는 @person으로 찾지 않아도 됨
        Assertions.assertTrue(parser.parseExpression("over(155)").getValue(context, Boolean.class));

        context.setVariable("cat", cat); // #으로 찾기
        Assertions.assertFalse(parser.parseExpression("#cat.over(100)").getValue(context, Boolean.class));
    }
}

