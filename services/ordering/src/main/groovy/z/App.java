package z;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import z.application.commands.CreateOrderCommandHandler;
import z.application.integration.CheckedoutIntegrationEventHandler;
import z.common.CommandBus;
import z.common.CommandBusImpl;
import z.domain.OrderRepository;
import z.eventbus.IntegrationEvent;
import z.eventbus.IntegrationEventHandler;
import z.eventbus.SubscriberManager;
import z.eventbus.impl.InMemorySubscriptionManager;
import z.eventbus.impl.RabbitMqBus;

@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    CommandLineRunner runner(RabbitMqBus bus, SimpleMessageListenerContainer listenerContainer, IntegrationEventHandler integrationEventHandler) {
        return args -> {
            bus.subscribe(new IntegrationEvent("{}"), integrationEventHandler);
            bus.configureListenerContainer(listenerContainer);
        };
    }

    @Configuration
    static class Config {
        @Bean
        RabbitMqBus rabbitMqBus(RabbitAdmin rabbitAdmin, SubscriberManager subscriberManager) {
            return new RabbitMqBus(rabbitAdmin, "a.b.order", subscriberManager);
        }

        @Bean
        SubscriberManager subscriberManager() {
            return new InMemorySubscriptionManager();
        }

        @Bean
        SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory) {
            SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
            return listenerContainer;
        }

        @Bean
        RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }
    }

    @Configuration
    static class ConfigEvent {
        @Bean
        CheckedoutIntegrationEventHandler checkedoutIntegrationEventHandler(CommandBus commandBus) {
            return new CheckedoutIntegrationEventHandler(commandBus);
        }

        @Bean
        CommandBus commandBus(ApplicationContext context) {
            return new CommandBusImpl(context);
        }

        @Bean
        CreateOrderCommandHandler createOrderCommandHandler(OrderRepository repository) {
            return new CreateOrderCommandHandler(repository);
        }
    }
}
