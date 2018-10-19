package z.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CatalogController {
    private final static Logger log = LoggerFactory.getLogger(CatalogController.class);

    private final Scheduler scheduler;
    private final JdbcTemplate jdbcTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CatalogController(Scheduler scheduler, JdbcTemplate jdbcTemplate, RabbitTemplate rabbitTemplate) {
        this.scheduler = scheduler;
        this.jdbcTemplate = jdbcTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(path = "/api/v1/catalog/items", produces = APPLICATION_JSON_VALUE)
    public Mono<String> hello() {
        Callable<List<String>> callable = () -> jdbcTemplate.queryForList("select name from catalog", String.class);
        Mono<List<String>> mono = Mono.fromCallable(callable)
            .publishOn(scheduler);

        rabbitTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", "Hello from RabbitMQ!");
        return mono.map(s -> asString(s));
    }

    private String asString(List<String> s) {
        return s.stream().collect(Collectors.joining(","));
    }
}
