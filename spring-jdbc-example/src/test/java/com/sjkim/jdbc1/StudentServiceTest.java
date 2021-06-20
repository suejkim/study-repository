package com.sjkim.jdbc1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
    private final StudentService studentService = new StudentService();

    @Test
    void add() throws Exception {
        Student student = new Student("김똑순", 10, LocalDate.of(2020, 1, 1), "010-1111-2222");
        boolean result = studentService.add(student);
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void update() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setBirth(LocalDate.of(2020, 1,2));
        boolean result = studentService.update(student);
        assertEquals(Boolean.TRUE, result);
    }

}