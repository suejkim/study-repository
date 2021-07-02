package com.sjkim.jdbc2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

//@RequiredArgsConstructor
public class StudentService {

    private Connection conn;

    public StudentService() {
        conn = DBConn.getConnection();
    }

    public boolean add(Student student) {
        PreparedStatement psm = null;
        String sql = "insert into student(name, age, birth, phone) values (?, ?, ?, ?)";

        try {
            psm = conn.prepareStatement(sql);
            psm.setString(1, student.getName());
            psm.setInt(2, student.getAge());
            psm.setDate(3, Date.valueOf(student.getBirth()));
            psm.setString(4, student.getPhone());
            psm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                try {
                    psm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        DBConn.close();
        return true;
    }

    public boolean update(Student student) {
        return true;
    }

    public boolean delete(long id) {
        return true;
    }

    public Student get(long id) {
        return null;
    }

    public List<Student> getAll() {
        return null;
    }
}
