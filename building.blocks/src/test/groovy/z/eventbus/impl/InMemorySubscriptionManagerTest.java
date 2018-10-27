package z.eventbus.impl;

import org.junit.Test;
import z.eventbus.IntegrationEvent;
import z.eventbus.IntegrationEventHandler;
import z.eventbus.SubscriberManager;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InMemorySubscriptionManagerTest {
    @Test
    public void testGetbackTheSameEventHandlerForDifferentEventsWithSameType() {
        SubscriberManager manager = new InMemoerySubscriptionManager();
        IntegrationEvent publishedEvent = new IntegrationEvent("{}");
        IntegrationEventHandler handler = event -> {
        };

        manager.add(publishedEvent, handler);

        IntegrationEvent receivedEvent = new IntegrationEvent("{}");
        List<IntegrationEventHandler> eventHandlers = manager.getEventHandlers(receivedEvent);

        assertEquals(1, eventHandlers.size());
    }
}