package com.sjkim.jdbc2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DBConnTest {

    @Test
    void getConnectionTest() {
        Connection connection = DBConn.getConnection();
        Assertions.assertNotNull(connection);
    }

    @Test
    void getConnectionCaseConnIsNotNullTest() {
        Connection connection = DBConn.getConnection();
        Assertions.assertNotNull(connection);
        DBConn.close();
    }
}