package com.sjkim.jdbc2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void updateStudentTest() throws Exception {
        LocalDate birth = LocalDate.now().plusDays(1L);
        List<Student> students = studentService.getAll();
        if (students.isEmpty()) {
            Student student = addStudent();
            studentService.add(student);
            students = studentService.getAll();
        }
        Student student = students.get(0);
        student.setBirth(birth);
        boolean result = studentService.update(student);
        assertEquals(Boolean.TRUE, result);

        Student updatedStudent = studentService.get(student.getId());
        assertThat(student.getId(), equalTo(updatedStudent.getId()));
    }

    @Test
    void countAllTest() throws Exception {
        int count = studentService.countAll();
        List<Student> students = studentService.getAll();
        assertEquals(students.size(), count);
    }

    @Test
    void getAllTest() throws Exception {
        List<Student> students = studentService.getAll();
        int count = studentService.countAll();
        assertThat(students.size(), equalTo(count));
    }

    @Test
    void deleteStudentTest() throws Exception {
        List<Student> students = studentService.getAll();
        if (students.isEmpty()) {
            Student student = addStudent();
            studentService.add(student);
            students = studentService.getAll();
        }
        long id = students.get(0).getId();
        boolean result = studentService.delete(id);
        assertEquals(Boolean.TRUE, result);
        Student deletedStudent = studentService.get(id);
        assertNull(deletedStudent);
        assertThat(deletedStudent, nullValue());
    }

    @Test
    void getStudentTest() throws Exception {
        List<Student> students = studentService.getAll();
        if (students.isEmpty()) {
            Student student = addStudent();
            studentService.add(student);
            students = studentService.getAll();
        }
        long id = students.get(0).getId();
        Student getStudent = studentService.get(id);
        Assertions.assertEquals(id, getStudent.getId());
    }
}
