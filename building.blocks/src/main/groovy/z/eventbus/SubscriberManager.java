package z.eventbus;

import java.util.List;

public interface SubscriberManager {
    void add(IntegrationEvent event, IntegrationEventHandler handler);
    List<IntegrationEventHandler> getEventHandlers(IntegrationEvent event);
}

