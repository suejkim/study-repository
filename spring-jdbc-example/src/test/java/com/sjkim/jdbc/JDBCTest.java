package com.sjkim.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class JDBCTest {

    @BeforeAll
    static void setUp() throws Exception {
//        Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
        Driver.class.getDeclaredConstructor().newInstance();
    }

    @Test
    void testConnection() throws Exception {
        String url = "jdbc:mariadb://localhost:3306/school";
        String user = "sjkim";
        String password = "password";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            assertThat(conn, notNullValue());
        }
    }
}
