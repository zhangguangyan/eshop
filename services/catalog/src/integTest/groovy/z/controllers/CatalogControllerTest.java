package z.controllers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:/application.local.properties"})
public class CatalogControllerTest {

    @Autowired
    private CatalogController catalogController;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        this.webTestClient = WebTestClient.bindToController(catalogController)
            .configureClient()
            .baseUrl("/api/v1/catalog")
            .build();
    }

    @Test
    public void testGetAllItems() {
        this.webTestClient.get().uri("/items")
            .exchange()
            .expectStatus().isOk()
            .expectBody();
    }
}
