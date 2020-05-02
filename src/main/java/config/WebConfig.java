package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import web.HelloController;

@Import(DbConfig.class)
@Configuration
public class WebConfig {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Bean
    public HelloController helloController(){
        return new HelloController(namedParameterJdbcTemplate);
    }
    
}