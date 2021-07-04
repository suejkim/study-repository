package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariadbConnection implements ConnectionFactory {

    private Connection conn;
    private final String driverClassName;
    private final String url;
    private final String user;
    private final String password;

    public MariadbConnection(String driverClassName, String url, String user, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(driverClassName);
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void close() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = null;
    }
}
