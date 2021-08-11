package config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
class JavaConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void hikariDataSourceTest() throws Exception {
        Connection conn = dataSource.getConnection();
        Assertions.assertNotNull(conn);
        conn.close();
    }
}
