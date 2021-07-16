package db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="/applicationContext.xml")
class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void hikariConfigDataSourceTest() {
        try {
            Connection conn = dataSource.getConnection();
            Assertions.assertNotNull(conn);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
