package z.eventbus.impl;

import z.eventbus.IntegrationEvent;
import z.eventbus.IntegrationEventHandler;
import z.eventbus.SubscriberManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoerySubscriptionManager implements SubscriberManager {
    private final Map<String, List<IntegrationEventHandler>> subscriptions = new HashMap<>();

    @Override
    public void add(IntegrationEvent event, IntegrationEventHandler handler) {
        String eventType = event.type();
        List<IntegrationEventHandler> handlers = subscriptions.get(eventType);
        if (handlers != null) {
            handlers.add(handler);
        } else {
            handlers = new ArrayList<>();
            handlers.add(handler);
            subscriptions.put(eventType, handlers);
        }
    }

    @Override
    public List<IntegrationEventHandler> getEventHandlers(IntegrationEvent event) {
        String eventType = event.type();
        return subscriptions.get(eventType);
    }
}
