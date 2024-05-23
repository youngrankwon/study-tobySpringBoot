package tobyspring.myboot;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class MybootApplication {

    private final JdbcTemplate jdbcTemplate;

    public MybootApplication(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {
        SpringApplication.run(MybootApplication.class, args);
    }

}
