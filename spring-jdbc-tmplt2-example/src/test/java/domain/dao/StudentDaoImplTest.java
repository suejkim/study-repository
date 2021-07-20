package domain.dao;

import config.JavaConfig;
import domain.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class StudentDaoImplTest {

    @Autowired
    private StudentDaoImpl studentDao;

    private Student addStudent() {
        return new Student("STUDENT", 10, LocalDate.now(), "010-1111-2222");
    }

    @Test
    void add() {
        Student student = addStudent();
        boolean result = studentDao.add(student);
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void update() {
        List<Student> students = getStudents();
        Student student = students.get(0);
        LocalDate birth = LocalDate.now().plusDays(1L);
        student.setBirth(birth);
        boolean result = studentDao.update(student);
        assertEquals(Boolean.TRUE, result);
        Student updatedStudent = studentDao.get(student.getId());
        assertThat(student.getId(), equalTo(updatedStudent.getId()));
    }

    @Test
    void delete() {
        List<Student> students = getStudents();
        long id = students.get(0).getId();
        boolean result = studentDao.delete(id);
        assertEquals(Boolean.TRUE, result);
        Student deletedStudent = studentDao.get(id);
        assertNull(deletedStudent);
        assertThat(deletedStudent, nullValue());
    }

    @Test
    void get() {
        List<Student> students = getStudents();
        long id = students.get(0).getId();
        Student getStudent = studentDao.get(id);
        Assertions.assertEquals(id, getStudent.getId());
    }

    @Test
    void getAll() {
        List<Student> students = studentDao.getAll();
        int count = studentDao.countAll();
        assertThat(students.size(), equalTo(count));
    }

    @Test
    void countAll() {
        int count = studentDao.countAll();
        List<Student> students = studentDao.getAll();
        assertEquals(students.size(), count);
    }

    private List<Student> getStudents() {
        List<Student> students = studentDao.getAll();
        if (students.isEmpty()) {
            Student student = addStudent();
            studentDao.add(student);
            students = studentDao.getAll();
        }
        return students;
    }
}