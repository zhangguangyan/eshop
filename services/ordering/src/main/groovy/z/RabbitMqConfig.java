package z;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class RabbitMqConfig {
    public static final String topicExchangeName = "eshop-exchange";

    static final String queueName = "eshop.catalog.ordering";

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName, true, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("eshop.catalog");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, HelloRabbit helloRabbit) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(message -> {
            helloRabbit.receiveMessage(new String(message.getBody(), UTF_8));
        });
        return container;
    }
}
