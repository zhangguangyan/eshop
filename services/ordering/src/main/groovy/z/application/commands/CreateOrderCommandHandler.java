package z.application.commands;

import org.springframework.beans.factory.annotation.Autowired;
import z.common.CommandHandler;
import z.domain.Order;
import z.domain.OrderRepository;

public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderRepository orderRepository;

    @Autowired
    public CreateOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) {
        // how to do validation as this is an async event?
        Order order = new Order();
        orderRepository.save(order);
    }
}
