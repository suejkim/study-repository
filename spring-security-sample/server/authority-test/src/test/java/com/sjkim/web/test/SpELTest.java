package com.sjkim.web.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

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

class SpELTest {

    private final ExpressionParser parser = new SpelExpressionParser();
    private static final String NAME = "KIM";
    private final Person person = Person.builder().name(NAME).height(160).build();

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
}

