package z.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:/application.local.properties"})
public class BasketControllerTest {

    @Autowired
    private BasketController basketController;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        this.webTestClient = WebTestClient.bindToController(basketController)
            .configureClient()
            .baseUrl("/api/v1/basket")
            .build();
    }

    @Test
    public void testPostBasket() {
        this.webTestClient.post().uri("/checkout")
            .body(Mono.just(TestData.getJson()), String.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody();
    }
}
