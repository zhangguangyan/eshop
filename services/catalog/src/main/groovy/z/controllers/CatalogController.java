package z.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CatalogController {
    private final static Logger log = LoggerFactory.getLogger(CatalogController.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatalogController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(path = "/hello", produces = APPLICATION_JSON_VALUE)
    public String hello() {
        List<String> name = jdbcTemplate.queryForList("select name from catalog", String.class);
        log.info("returned from db: {}", name);
        return name.stream()
            .collect(Collectors.joining(","));
    }
}
