package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import domain.dao.StudentDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JavaConfig {

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
//        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/school");
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mariadb://localhost:3306/school");
        hikariConfig.setUsername("sjkim");
        hikariConfig.setPassword("password");
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public StudentDaoImpl studentDaoImpl(JdbcTemplate jdbcTemplate) {
        return new StudentDaoImpl(jdbcTemplate);
    }
}
