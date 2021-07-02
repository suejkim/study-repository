package com.sjkim.jdbc2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public boolean add(Student student) {
        Connection conn = DBConn.getConnection();
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
        Connection conn = DBConn.getConnection();
        PreparedStatement psm = null;
        String sql = "update student set birth = ? where id = ?";
        try {
            psm = conn.prepareStatement(sql);
            psm.setDate(1, Date.valueOf(student.getBirth()));
            psm.setLong(2, student.getId());
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

    public boolean delete(long id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psm = null;
        String sql = "delete from student where id = ?";
        try {
            psm = conn.prepareStatement(sql);
            psm.setLong(1, id);
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

    public Student get(long id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psm = null;
        ResultSet resultSet = null;
        Student student = null;
        String sql = "select * from student where id = ?";
        try {
            psm = conn.prepareStatement(sql);
            psm.setLong(1, id);
            resultSet = psm.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setBirth(resultSet.getDate("birth").toLocalDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (psm != null) {
                try {
                    psm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        DBConn.close();
        return student;
    }

    public List<Student> getAll() {
        Connection conn = DBConn.getConnection();
        PreparedStatement psm = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        try {
            psm = conn.prepareStatement(sql);
            resultSet = psm.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setBirth(resultSet.getDate("birth").toLocalDate());
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (psm != null) {
                try {
                    psm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        DBConn.close();
        return students;
    }

    public int countAll() {
        Connection conn = DBConn.getConnection();
        int count = 0;
        PreparedStatement psm = null;
        ResultSet resultSet = null;
        String sql = "select count(*) from student";
        try {
            psm = conn.prepareStatement(sql);
            resultSet = psm.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (psm != null) {
                try {
                    psm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        DBConn.close();
        return count;
    }
}
