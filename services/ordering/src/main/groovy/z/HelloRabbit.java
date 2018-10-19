package z;

import org.springframework.stereotype.Component;

@Component
public class HelloRabbit {
    public void receiveMessage(String what) {
        System.out.println("==============================================================");
        System.out.println(what);
    }
}
