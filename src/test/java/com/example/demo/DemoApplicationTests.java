package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        String str ="The timeout used to detect consumer failures when using " +
                "Kafka's group management facility. The consumer sends periodic heartbeats to indicate its liveness " +
                "to the broker. If no heartbeats are received by the broker before the expiration of this session timeout, " +
                "then the broker will remove this consumer from the group and initiate a rebalance. Note that the value " +
                "must be in the allowable range as configured in the broker configuration by <code>group.min.session.timeout.ms</code> " +
                "and <code>group.max.session.timeout.ms</code>.";
        System.out.println(str);
    }

}
