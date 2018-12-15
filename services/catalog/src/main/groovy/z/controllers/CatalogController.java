package z.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import z.eventbus.EventBus;
import z.eventbus.IntegrationEvent;

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
    private final EventBus eventBus;

    @Value("${app.picture.server.url}")
    private String pictureServerUrl;

    @Autowired
    public CatalogController(Scheduler scheduler, JdbcTemplate jdbcTemplate, EventBus eventBus) {
        this.scheduler = scheduler;
        this.jdbcTemplate = jdbcTemplate;
        this.eventBus = eventBus;
    }

    @GetMapping(path = "/items", produces = APPLICATION_JSON_VALUE)
    public Flux<CatalogItem> findAllItems() {
        Callable<List<CatalogItem>> callable = () -> jdbcTemplate.query("select * from catalog_item", (rs, rowNum) ->
            new CatalogItem(
                rs.getInt("item_id"),
                rs.getString("description"),
                rs.getString("name"),
                pictureServerUrl + "/" + rs.getString("picture_filename"),
                rs.getBigDecimal("price"),
                rs.getInt("available_stock"),
                rs.getInt("max_stock_threshold"),
                rs.getBoolean("on_reorder"),
                rs.getInt("restock_threshold"),
                rs.getInt("catalog_brand_id"),
                rs.getInt("catalog_type_id")
            )
        );
        Flux<CatalogItem> itemFlux = Mono.fromCallable(callable)
            .publishOn(scheduler)
            .flatMapMany(Flux::fromIterable);

        return itemFlux;
    }

    @PostMapping(path = "/items", produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> createItem(@RequestBody CatalogItem1 catalogItem) {
        log.debug("create catalog item: {}", catalogItem);
        Callable<Integer> callable = () -> {
            int rows = jdbcTemplate.update("insert into catalog(name) values(?)", catalogItem.getName());
            String event = EventBuilder.productAdded(catalogItem);
            eventBus.publish(new IntegrationEvent(String.format("{\"id\":%s}", event)));
            return rows;
        };
        Mono<Integer> mono = Mono.fromCallable(callable)
            .publishOn(scheduler);
        return mono.map(s -> s > 0 ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping(path = "/items/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> updateItem(@PathVariable("id") String id) {
        eventBus.publish(new IntegrationEvent(String.format("{\"id\":%s}", id)));
        return Mono.just(new ResponseEntity<>(HttpStatus.OK));
    }

    private static String asString(List<String> s) {
        return s.stream().collect(Collectors.joining(","));
    }
}
