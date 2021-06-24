package com.sjkim.jdbc2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

    // singleton pattern 적용. 많은 스레드가 이 인스턴스를 공유하는데.. thread safety는 의문.

    private static Connection conn;
    private static final Logger LOGGER = LoggerFactory.getLogger(DBConn.class);
    private static final String URL = "jdbc:mariadb://localhost:3306/school";
    private static final String USER = "sjkim";
    private static final String PASSWORD = "password";

    private DBConn() {
    } // 외부에서 객체 생성 막음

    public static Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                LOGGER.info("------------------ Connection");
            }
        } catch (Exception e) {
            LOGGER.error("getConnection Fail - {}", e.getMessage());
        }
        return conn;
    }

    public static void close() {
        try {
            if (!conn.isClosed()) {
                conn.close();
                LOGGER.info("------------------ Connection Close");
            }
        } catch (Exception e) {
            LOGGER.error("close Fail - {}", e.getMessage());
        }
        conn = null;
    }
}
