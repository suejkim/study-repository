package domain.dao;

import domain.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements CommonDao<Student> {

    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(Student student) {
        String sql = "insert into student(name, age, birth, phone) values (?, ?, ?, ?)";
        int result = jdbcTemplate.update(
                sql,
                student.getName(),
                student.getAge(),
                student.getBirth(),
                student.getPhone()
        );
        return result > 0;
    }

    @Override
    public boolean update(Student student) {
        String sql = "update student set name = ?, age = ?, birth = ?, phone = ? where id = ?";
        int result = jdbcTemplate.update(
                sql,
                student.getName(),
                student.getAge(),
                student.getBirth(),
                student.getPhone(),
                student.getId()
        );
        return result > 0;
    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from student where id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0;
    }

    @Override
    public Student get(long id) {
        String sql = "select * from student where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new RowMapper<Student>() {
                    @Override
                    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Student student = new Student();
                        student.setId(rs.getLong("id"));
                        student.setName(rs.getString("name"));
                        student.setAge(rs.getInt("age"));
                        student.setBirth(rs.getDate("birth").toLocalDate());
                        return student;
                    }
                },
                id
        );
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from student";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<Student> students = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            Student student = new Student();
            student.setId((Long) map.get("id"));
            student.setName((String) map.get("name"));
            student.setAge((Integer) map.get("age"));
            student.setBirth((LocalDate) map.get("birth"));
            students.add(student);
        }
        return students;
    }

    @Override
    public int countAll() {
        String sql = "select count(*) from student";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        return result != null ? result : -1;
    }
}
