package z.common;

import org.springframework.context.ApplicationContext;
import z.application.commands.CreateOrderCommandHandler;
import z.application.commands.CreateOrderCommand;

import java.util.HashMap;

public class CommandBusImpl implements CommandBus {
    private final HashMap<Class<?>, CommandHandler> routes = new HashMap<>();


    public CommandBusImpl(ApplicationContext context) {
        registerCommandHandler(CreateOrderCommand.class, context.getBean(CreateOrderCommandHandler.class));
    }

    @Override
    public void send(Command command) {
        CommandHandler commandHandler = routes.get(command.getClass());
        commandHandler.handle(command);
    }

    private <T extends Command> void registerCommandHandler(Class<T> commandType, CommandHandler<T> commandHandler) {
        routes.put(commandType, commandHandler);
    }
}
