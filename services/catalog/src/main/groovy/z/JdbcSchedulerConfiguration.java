package z;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class JdbcSchedulerConfiguration {
    private final int connectionPoolSize;

    public JdbcSchedulerConfiguration(@Value("${spring.datasource.hikari.maximum-pool-size}") int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }
    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}
