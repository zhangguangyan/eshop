package z.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import z.domain.Order;
import z.domain.OrderRepository;

@Repository
public class OrderRepositoryIml implements OrderRepository {
    private Logger LOG = LoggerFactory.getLogger(OrderRepositoryIml.class);

    @Override
    public void save(Order order) {
        LOG.debug("saving order");
    }
}
