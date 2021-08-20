package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class DemoApplicationTests {

    private final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);


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


    @Test
    public void test001(){

//        MyThreadTest threadTest = new MyThreadTest("消息");
//        threadTest.run();
        myThreadTest02 threadTest02 = new myThreadTest02("自己的消息");
        threadTest02.run();
    }

    @Test
    public void test002(){
        int i = Math.abs("console-consumer-46965".hashCode()) % 50;
        System.out.println(i);
    }

    static class myThreadTest02 implements Runnable{

        private final Logger log = LoggerFactory.getLogger(myThreadTest02.class);

        public String str;

        public myThreadTest02(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println("sout的"+str);
            log.info("log的"+str);
        }
    }

}
