package com.sjkim.jdbc1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public boolean add(Student student) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
//        Driver.class.getDeclaredConstructor().newInstance();
        // Class.forName(). class 이름으로부터 class 객체 참조 얻기
        // 주로 DB Driver를 메모리에 올릴 때 사용

        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password); // Driver 연결

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

    public boolean update(Student student) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();

        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password); // Driver 연결

        PreparedStatement psm = conn.prepareStatement("update student set birth = ? where id = ?");
        psm.setDate(1, Date.valueOf(student.getBirth()));
        psm.setLong(2, student.getId());
        psm.execute();

        psm.close();
        conn.close();
        return true;
    }

    public boolean delete(long id) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();

        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement psm = conn.prepareStatement("delete from student where id = ?");
        psm.setLong(1, id);
        psm.execute();

        psm.close();
        conn.close();
        return true;
    }

    public Student get(long id) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();

        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement psm = conn.prepareStatement("select * from student where id = ?");
        psm.setLong(1, id);

        ResultSet resultSet = psm.executeQuery();
        Student student = null;
        if (resultSet.next()) {
            student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setBirth(resultSet.getDate("birth").toLocalDate());
        }

        resultSet.close();
        psm.close();
        conn.close();
        return student;
    }

    public List<Student> getAll() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();

        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement psm = conn.prepareStatement("select * from student");
        ResultSet resultSet = psm.executeQuery();
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setBirth(resultSet.getDate("birth").toLocalDate());
            students.add(student);
        }

        resultSet.close();
        psm.close();
        conn.close();
        return students;
    }

    public int countAll() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();

        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement psm = conn.prepareStatement("select count(*) from student");
        ResultSet resultSet = psm.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        resultSet.close();
        psm.close();
        conn.close();
        return count;
    }
}
