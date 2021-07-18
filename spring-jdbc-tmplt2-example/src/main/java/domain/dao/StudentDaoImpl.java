package domain.dao;

import domain.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDaoImpl implements CommonDao<Student> {

    private final JdbcTemplate jdbcTemplate;
    public StudentDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean add(Student student) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student student) throws Exception {
        return false;
    }

    @Override
    public boolean delete(long id) throws Exception {
        return false;
    }

    @Override
    public Student get(long id) throws Exception {
        return null;
    }

    @Override
    public List<Student> getAll() throws Exception {
        return null;
    }

    @Override
    public int countAll() throws Exception {
        return 0;
    }
}
