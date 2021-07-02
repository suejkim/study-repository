package com.sjkim.jdbc2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StudentServiceTest {

    private final StudentService studentService = new StudentService();

    private Student addStudent() {
        return new Student("이똑순", 10, LocalDate.now(), "010-1111-2222");
    }

    @Test
    void addStudentTest() throws Exception {
        Student student = addStudent();
        boolean result = studentService.add(student);
        assertThat(result, is(Boolean.TRUE));
    }

}
