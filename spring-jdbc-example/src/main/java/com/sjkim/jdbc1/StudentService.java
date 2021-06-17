package com.sjkim.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class StudentService {

    public boolean add(Student student) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();  // ??
        String url = "jdbc:mariadb://localhost:3306/school";
        Connection conn = DriverManager.getConnection(url, "sjkim", "password"); // Driver 연결

        PreparedStatement psm = conn.prepareStatement("insert into student(name, age, birth, phone) values (?, ?, ?, ?)");
        psm.setString(1, student.getName());
        psm.setInt(2, student.getAge());
        psm.setDate(3, Date.valueOf(student.getBirth()));
        psm.setString(4, student.getPhone());
        psm.execute();

        psm.close();
        conn.close();
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

    public int countAll() {
        return 0;
    }
}
