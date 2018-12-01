package z.application.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z.application.commands.CreateOrderCommand;
import z.common.CommandBus;
import z.eventbus.IntegrationEvent;
import z.eventbus.IntegrationEventHandler;

public class CheckedoutIntegrationEventHandler implements IntegrationEventHandler {
    private static final Logger log = LoggerFactory.getLogger(CheckedoutIntegrationEventHandler.class);

    private final CommandBus commandBus;

    public CheckedoutIntegrationEventHandler(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void handle(IntegrationEvent event) {
        log.debug("=========== consumed by ordering service ===============");
        log.debug(event.toString());
        commandBus.send(new CreateOrderCommand());
        log.debug("===================================");
    }
}
