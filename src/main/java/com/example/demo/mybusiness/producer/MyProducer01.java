package com.example.demo.mybusiness.producer;

import com.example.demo.Constant.TopicName;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer01 {

    private static Properties getProps(){
        Properties props =  new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        //acks参数 详解     1：leader接收成功则算成功（默认）
        //           all -1：leader接收成功，且follower 同步（isr）成功才算成功,否则算失败会重试 （副本需>=2才有实际意义）
        //                0：不用管成功还是失败
        props.put("acks", "all"); // 发送所有ISR
        props.put("retries", 2); // 重试次数

        //如果一批次没有填满这个大小的话，消息将不会发送，下一条b进来后，如果大小溢出的话，就会将之前的消息全部发送出去，b作为下一次batch的第一条。
        //如果没有溢出，刚好满的话就一起发送了。(除了消息的大小还会有producer的元数据大小。）
        //kafka会将多个batch打包成一个request通过producer的sender线程发送出去  发送到broker
        // 可以通过 "max.request.size"设置一次request的大小
        //batch的大小最好是具体消息大小的几倍，否则一下就满了，没有缓存的作用
        props.put("batch.size", 1000); // 批量发送大小 单位字节KB
        //可以配合 linger.ms使用。如果消息一直没有填满这个大小，那么过了多少ms后也会发送
        props.put("linger.ms", 1000); // 发送频率，满足任务一个条件发送


        //buffer.memory kafka不是有一条数据就发一条数据，而是将很多数据收集成一个一个的batch（batch.size）存储在内存中，再统一发送
        //如果内存区耗尽，将阻塞用户线程，将不再接收用户发送的消息(因为不能写入缓冲区)
        props.put("buffer.memory", 33554432); // 缓存大小，根据本机内存大小配置
        //可配合max.block.ms使用，超出最大阻塞时间后会抛出timeoutException，不设置的话不会抛错

        //逻辑应用名/别名 方便统计，否则是ip/port
        props.put("client.id", "producer-asyn-1"); // 发送端id,便于统计
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public static void main(String[] args) {
        sendSingleMsg("only key and value");
    }

    private static String topicSimple01= TopicName.TOPIC_SIMPLE_01;

    public static void sendSingleMsg(String msg){
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProps());
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicSimple01,i+"",msg+i);
            producer.send(record);
        }
        producer.flush();
        producer.close();
    }

}
