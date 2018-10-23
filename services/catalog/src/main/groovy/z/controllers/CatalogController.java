package z.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/catalog")
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

    @GetMapping(path = "/items", produces = APPLICATION_JSON_VALUE)
    public Mono<String> findAllItems() {
        Callable<List<String>> callable = () -> jdbcTemplate.queryForList("select name from catalog", String.class);
        Mono<List<String>> mono = Mono.fromCallable(callable)
            .publishOn(scheduler);

        return mono.map(s -> asString(s));
    }

    @PostMapping(path = "/items", produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> createItem(@RequestBody CatalogItem catalogItem) {
        log.debug("create catalog item: {}", catalogItem);
        Callable<Integer> callable = () -> {
            int rows = jdbcTemplate.update("insert  into catalog(name) values(?)", catalogItem.getName());
            rabbitTemplate.convertAndSend("eshop-exchange", "eshop.catalog", EventBuilder.productAdded(catalogItem));
            return rows;
        };
        Mono<Integer> mono = Mono.fromCallable(callable)
            .publishOn(scheduler);
        return mono.map(s -> s > 0 ? new ResponseEntity<>(HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    private static String asString(List<String> s) {
        return s.stream().collect(Collectors.joining(","));
    }
}
