package com.example.demo.mybusiness.consumer;


import com.example.demo.Constant.GroupName;
import com.example.demo.Constant.TopicName;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyConsumer01 {
    private static Properties getProps(){
        Properties props =  new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
//        props.put("group.id", GroupName.Group_Id_Simple_01);
        props.put("group.id", "testGroup001");
        props.put("session.timeout.ms", 8000);       // 如果其超时，将会可能触发rebalance并认为已经死去，重新选举Leader
        props.put("enable.auto.commit", "false");      // 开启自动提交
        props.put("auto.commit.interval.ms", "1000"); // 自动提交时间
        props.put("auto.offset.reset","earliest"); //earliest 从最早的offset开始拉取，latest:从最近的offset开始消费
        props.put("client.id", "producer-syn-1"); // 发送端id,便于统计
        props.put("max.poll.records","2"); // 每次批量拉取条数
        props.put("max.poll.interval.ms","1000");
        props.put("heartbeat.interval.ms","2000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("isolation.level","read_committed"); // 设置隔离级别
        return props;
    }

    private static final Logger log = LoggerFactory.getLogger(MyConsumer01.class);


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
//        processTopic001();
        ThreadTask01 task01 = new ThreadTask01(countDownLatch);
        ThreadTask01 task02 = new ThreadTask01(countDownLatch);
//        ThreadTask01 task03 = new ThreadTask01(countDownLatch);
        new Thread(task01).start();
        new Thread(task02).start();
//        new Thread(task03).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    public static void processTopic001(){
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProps());
        ArrayList<String> topics = new ArrayList<>();
        topics.add(TopicName.TOPIC_SIMPLE_01);
        consumer.subscribe(topics);
        for(;;){
            ConsumerRecords<String, String> cecord = consumer.poll(100);
            cecord.forEach((e)->{
                System.out.println("topic:"+e.topic()+",offset:"+e.offset()+" ,key:"+e.key()+"   ,value:"+e.value()+"   partition:"+e.partition()+"    timestamp"+e.timestamp());
            });
        }

    }



    public static class ThreadTask01 implements Runnable{

        private final Logger log = LoggerFactory.getLogger(ThreadTask01.class);

        CountDownLatch countDownLatch;

        public ThreadTask01(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"is running");
//            log.info(threadName+"is running");
            Properties props = getProps();
            props.put("client.id", "producer-"+threadName);
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            ArrayList<String> topics = new ArrayList<>();
            topics.add(TopicName.TOPIC_SIMPLE_01);
            consumer.subscribe(topics);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName+"is countDown");
//            log.info(threadName+"is countDown");
            for(int i = 0 ;i < 10; i++){
                if ("Thread-1".equals(threadName)){
                    if(i==2){
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ConsumerRecords<String, String> cecord = consumer.poll(100);
                cecord.forEach((e)->{
                    System.out.println(threadName+"topic:"+e.topic()+",offset:"+e.offset()+" ,key:"+e.key()+"   ,value:"+e.value()+"   partition:"+e.partition()+"    timestamp"+e.timestamp());
//                    log.info("topic:"+e.topic()+",offset:"+e.offset()+" ,key:"+e.key()+"   ,value:"+e.value()+"   partition:"+e.partition()+"    timestamp"+e.timestamp());
                });
            }
            consumer.close();
        }
    }

}
