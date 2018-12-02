package z;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Configuration
@SpringBootApplication
public class App {
    private final String catalogApiUrl;
    private final String basketApiUrl;

    public App(@Value("${catalog.api.url}") String catalogApiUrl, @Value("${basket.api.url}") String basketApiUrl) {
        this.catalogApiUrl = catalogApiUrl;
        this.basketApiUrl = basketApiUrl;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("r1", r -> r.path("/api/v1/catalog/**")
                .uri(catalogApiUrl))
            .route("r2", r -> r.path("/api/v1/basket/**")
                .uri(basketApiUrl))
            .build();
    }

    @RestController
    static class AggregateController {
        @GetMapping("/api/v1/aggregate")
        public Mono<String> aggregate() {
            return Mono.just("Aggregate service from api gateway");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}