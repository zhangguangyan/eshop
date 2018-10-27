package z.eventbus.impl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import z.eventbus.*;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RabbitMqBus implements EventBus {
    public final static String BROKER_NAME = "eshop";

    private final RabbitAdmin rabbitAdmin;
    private final RabbitTemplate rabbitTemplate;
    private final String queueName;
    private final SubscriberManager subscriptionManager;
    private final DirectExchange exchange;
    private final Queue queue;

    /**
     * each application has one queue, which means one bus
     * @param rabbitAdmin
     * @param queueName
     * @param subscriptionManager
     */
    public RabbitMqBus( RabbitAdmin rabbitAdmin, String queueName, SubscriberManager subscriptionManager) {
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitAdmin.getRabbitTemplate();
        this.queueName = queueName;
        this.subscriptionManager = subscriptionManager;
        this.exchange = new DirectExchange(BROKER_NAME);
        rabbitAdmin.declareExchange(exchange);
        this.queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
    }

    @Override
    public void publish(IntegrationEvent event) {
        String routingKey = event.type();
        rabbitTemplate.convertAndSend(BROKER_NAME, routingKey, event.toString());
    }

    @Override
    public <E extends IntegrationEvent, H extends IntegrationEventHandler> void subscribe(E event, H handler) {
        String routingKey = event.type();
        subscriptionManager.add(event, handler);
        Binding binding = BindingBuilder.bind(queue)
            .to(exchange)
            .with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * this should be called after all event handlers are registered
     * @param container
     */
    public void configureListenerContainer(SimpleMessageListenerContainer container) {
        container.setQueueNames(queueName);
        container.setMessageListener(message -> {
            String body = new String(message.getBody(), UTF_8);
            IntegrationEvent event = new IntegrationEvent(body);
            List<IntegrationEventHandler> handlers = subscriptionManager.getEventHandlers(event);
            for (IntegrationEventHandler handler : handlers) {
                handler.handle(event);
            }
        });
    }
}
