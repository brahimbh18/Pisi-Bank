package PISI.BANK.Pisi.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:/home/brahim/Pisi-Bank/pisi.db");
        return dataSource;
    }

    @Bean
    public static JdbcTemplate jdbcTemplate() {
        DataSource d = dataSource();
        return new JdbcTemplate(d);
    }
}