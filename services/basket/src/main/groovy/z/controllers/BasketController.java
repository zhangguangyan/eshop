package z.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import z.eventbus.EventBus;
import z.eventbus.IntegrationEvent;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/basket")
@RestController
public class BasketController {
    private final static Logger log = LoggerFactory.getLogger(BasketController.class);

    private final EventBus eventBus;

    @Autowired
    public BasketController(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @PostMapping(path = "/checkout", produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> createItem(@RequestBody String catalogItem) {
        log.debug("create basket item: {}", catalogItem);
        eventBus.publish(new IntegrationEvent(catalogItem));
        Mono<Integer> mono = Mono.just(1);
        return mono.map(s -> s > 0 ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
