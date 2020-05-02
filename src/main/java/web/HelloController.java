package web;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HelloController(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GetMapping("/hello/{name}")
    public String helloName(
        @PathVariable(name="name") String name
    ){

        namedParameterJdbcTemplate.update(
            " insert into requested_names(name, process_time) " +
            " values (:name_ph, :process_time_ph)",
            new MapSqlParameterSource()
                .addValue("name_ph", name)
                .addValue("process_time_ph", Timestamp.from(Instant.now()))
        );
        
        return "Hello " + name;
    }

}