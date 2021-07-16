package db;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class MariadbConnectionTest {

    @Test
    void getConnection() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        MariadbConnection mariadbConnection = (MariadbConnection) context.getBean("mariadbConnection");
        Connection conn = mariadbConnection.getConnection();
        assertNotNull(conn);
        mariadbConnection.close();
        context.close();
    }
}