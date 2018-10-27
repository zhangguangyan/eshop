package z.eventbus;

public interface EventBus {
    void publish(IntegrationEvent integrationEvent);
    <E extends IntegrationEvent, H extends IntegrationEventHandler> void subscribe(E event, H handler);
}
