package config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
@PropertySource("classpath:application.properties")
public class DbConfig {

    @Value("${db.className}")
    private String className;

    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.pwd}")
    private String pwd;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
      return new NamedParameterJdbcTemplate(jdbcTemplate());
    }
}