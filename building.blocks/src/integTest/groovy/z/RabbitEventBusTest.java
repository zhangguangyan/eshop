package z;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import z.eventbus.IntegrationEvent;
import z.eventbus.IntegrationEventHandler;
import z.eventbus.impl.RabbitMqBus;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class RabbitEventBusTest {
    @Autowired
    RabbitMqBus eventBus;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Autowired
    SimpleMessageListenerContainer listenerContainer;

    @Test
    public void test01() throws InterruptedException {
        IntegrationEvent event = new IntegrationEvent("{}");
        IntegrationEventHandler handler = event1 -> {
            assertTrue("message received", event1 != null);
        };
        eventBus.subscribe(event, handler);
        eventBus.configureListenerContainer(listenerContainer);
        eventBus.publish(event);
        Thread.sleep(5000);
    }
}
