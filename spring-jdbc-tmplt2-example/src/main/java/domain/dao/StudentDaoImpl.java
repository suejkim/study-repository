package domain.dao;

import domain.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<Student> students = jdbcTemplate.query(sql, (rs, rowNum) -> convertResultSetToStudent(rs), id);
        return students.isEmpty() ? null : students.get(0);
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from student";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        List<Student> students = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            Student student = convertMapToStudent(map);
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

    private Student convertResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setPhone(rs.getString("phone"));
        student.setBirth(rs.getDate("birth").toLocalDate());
        return student;
    }

    private Student convertMapToStudent(Map<String, Object> map) {
        Student student = new Student();
        student.setId((Long) map.get("id"));
        student.setName((String) map.get("name"));
        student.setAge((Integer) map.get("age"));
        student.setPhone((String) map.get("phone"));
        student.setBirth(((Date) map.get("birth")).toLocalDate());
        return student;
    }
}
