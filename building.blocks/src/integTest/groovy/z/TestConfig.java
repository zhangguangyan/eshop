package z;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import z.eventbus.SubscriberManager;
import z.eventbus.impl.InMemoerySubscriptionManager;
import z.eventbus.impl.RabbitMqBus;

@Configuration
public class TestConfig {
    static String queueName = "a.b.c01";

    @Bean
    RabbitMqBus rabbitMqBus(RabbitAdmin rabbitAdmin, SubscriberManager subscriptionManager) {
        return new RabbitMqBus(rabbitAdmin, queueName, subscriptionManager);
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }

    @Bean
    SubscriberManager subscriptionManager() {
        return new InMemoerySubscriptionManager();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        return listenerContainer;
    }
}
