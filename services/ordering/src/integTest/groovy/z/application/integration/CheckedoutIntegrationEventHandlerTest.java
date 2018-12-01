package z.application.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import z.eventbus.IntegrationEvent;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = { "classpath:/app.local.properties" })
public class CheckedoutIntegrationEventHandlerTest {
    @Autowired
    private CheckedoutIntegrationEventHandler checkedoutIntegrationEventHandler;
    @Test
    public void testCreateOrder() {
        checkedoutIntegrationEventHandler.handle(new IntegrationEvent("{}"));
    }
}
