#  Kafka

## properties参数设置

### producer

```java
Properties props =  new Properties();
props.put("bootstrap.servers", "127.0.0.1:9092");
props.put("acks", "all"); // 发送所有ISR
props.put("retries", 2); // 重试次数
props.put("batch.size", 1000); // 批量发送大小 单位字节KB
props.put("linger.ms", 1000); // 发送频率，满足任务一个条件发送
props.put("buffer.memory", 33554432); // 缓存大小，根据本机内存大小配置
props.put("client.id", "producer-asyn-1"); // 发送端id,便于统计
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
```


| 参数          | 详解                                                         |
| :------------ | ------------------------------------------------------------ |
| acks          | 1：leader接收成功则算成功（默认）<br/> all -1：leader接收成功，且follower 同步（isr）成功才算成功,否则算失败会重试 （副本需>=2才有实际意义）<br/> 0：不用管成功还是失败 |
| batch.size    | 如果一批次没有填满这个大小的话，消息将不会发送，下一条b进来后，如果大小溢出的话，就会将之前的消息全部发送出去，b作为下一次batch的第一条。<br/> 如果没有溢出，刚好满的话就一起发送了。(除了消息的大小还会有producer的元数据大小。）<br/> kafka会将多个batch打包成一个request通过producer的sender线程发送出去  发送到broker<br/> 可以通过 "max.request.size"设置一次request的大小<br/>batch的大小最好是具体消息大小的几倍，否则一下就满了，没有缓存的作用 |
| linger.ms     | 可以配合 linger.ms使用。如果消息一直没有填满这个大小，那么过了多少ms后也会发送 |
| buffer.memory | kafka不是有一条数据就发一条数据，而是将很多数据收集成一个一个的batch（batch.size）存储在内存中，再统一发送<br/> 如果内存区耗尽，将阻塞用户线程，将不再接收用户发送的消息(因为不能写入缓冲区)<br/>可配合max.block.ms使用，超出最大阻塞时间后会抛出timeoutException，不设置的话不会抛错,但还是会一直阻塞 |
| max.block.ms  | 最大阻塞时间，超时报错timeoutException                       |
| client.id     | 别名 方便统计                                                |

### consumer

```java
Properties props =  new Properties();
props.put("bootstrap.servers", "127.0.0.1:9092");
props.put("group.id", "test_3");
props.put("session.timeout.ms", 30000); // 如果其超时，将会可能触发rebalance并认为已经死去，重新选举Leader
props.put("enable.auto.commit", "true");      // 开启自动提交
props.put("auto.commit.interval.ms", "1000"); // 自动提交时间
props.put("auto.offset.reset","earliest"); // 从最早的offset开始拉取，latest:从最近的offset开始消费
props.put("client.id", "producer-syn-1"); // 发送端id,便于统计
props.put("max.poll.records","100"); // 每次批量拉取条数
props.put("max.poll.interval.ms","1000");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("isolation.level","read_committed"); // 设置隔离级别
return props;
```



| 参数                                                        | 详解                                                         |
| :---------------------------------------------------------- | ------------------------------------------------------------ |
| group.id                                                    | 需显示指定。                                                 |
| <font color="red">**session.timeout.ms**</font><br/>默认10s | <font color='red'>**group coordinator**</font> 检测失败的时间，当这个阈值时间内从未收到consumer的任何消息，消费组协调组就认为这个consumer挂掉了 已经追不上其他成员的消费速度了，就会将这个consumer"踢出"这个组，将该consumer负责的分区分配给其他consumer。<br/><font color='red'>最好</font>的情况下，只是会触发不必要的rebalance。<br/><font color='red'>更坏</font>的情况，被"踢出"后处理的消息不能提交位移，这会导致<font color='red'>重新</font>消费一遍 |
| max.poll.interval.ms<br/>默认5min，一般设置10s              | 两次poll消息的间隔时间。拉取到消息后，预计处理时间           |
| heartbeat.interval.ms<br/>默认3s                            | 通过response返回通知group coordinator他还或者，否则"死了"就需要rebalance了，**该值必须小于 session.timeout.ms**,否则没有意义 |
| max.poll.records<br/>默认500                                | 一次最大拉取消息数量，如果拉太多一次没处理玩，就会触发rebalance |
| enable.auto.commit                                          | 默认 true                                                    |
| auto.commit.interval.ms                                     | 默认 5s                                                      |
| fetch.max.bytes                                             | 指定获取数据的最大字节数                                     |
| max.poll.records<br/>默认  500                              | 控制单次poll返回最大消息数                                   |
| connections.max.idle.ms<br/>默认 9min                       | kafka会周期性的删除空闲链接，如果设置为-1则不会删除。        |

> session.timeout.ms和max.poll.interval.ms和heartbeat.interval.ms的**区别**
>
> &ensp;&ensp;&ensp;&ensp;session.timeout.ms是要超过这个阈值时间 group coordinate没有收到consumer的**<font color='red'>任何</font>**信息，就认为consumer挂掉了，因为都挂掉了所以就不用吧rebalance的信息返回给该consumer了。
>
> &ensp;&ensp;&ensp;&ensp;max.poll.interval.ms是每次poll之后处理间隔，如果消费者没有消费完这个数据，说明消费者有问题，就让别的消费者来处理，（如果超过了该间隔consumer client会主动向coordinator发起LeaveGroup请求，触发rebalance；然后consumer重新发送JoinGroup请求）。
>
> > 一般时间间隔会小于session.timeout.ms所以不用等到阈值才rebalance
>
> &ensp;&ensp;&ensp;&ensp;heartbeat.interval.ms是设置一个时间间隔，consumer需要主动发送一个心跳信息告诉group coordinator 它还活着，间隔越小 发TCP包的数量就越多。**该值必须<font color='red'>小于</font> session.timeout.ms**,否则没有意义。
>
> > 因为 session.timeout.ms 的时间间隔到了group coordinator就认为这个consumer已经挂掉了。
> >
> > kafka的<font color='red'>处理消息</font>和<font color='red'>心跳机制</font>是两个不同的线程，心跳不用等处理完消息就会给group coordinator通信，相当于session.timeout.ms就会重新计时 ，所以该值必须<font color='red'>小于</font>session.timeout.ms。

## KafkaProducer

### producer的连接问题

- 如社区文档所说，**KafkaProducer**类是线程**安全**的，producer主线程和Sender线程共享的可变数据结构大概就只有**RecordAccumulator**类<br>因此维护RecordAccumulator类的线程安全也就实现了KafkaProducer的线程安全，而RecordAccumulator类中主要的数据结构是`ConcurrentMap<TopicPartition,Deque<ProducerBatch>>`<br>而且凡是用到Deque的地方基本上都由**Java monitor lock**来保护，所以基本上可以认定RecordAccumulator的线程安全性。

- producer在创建实例的时候会创建并启动sender线程，sender线程开始运行就会和s<font color='red'>所有</font>broker建立tcp连接。会发送METADATA去通过<font color='red'>一条</font>TPC连接请求集群的元数据，此时TCP连接中的<font color='red'>broker.id</font>是假的，为负值，因为他不知道真实的<font color='red'>broker.id</font>

  会请求当前负载最小的broker，负载最小指当前未完成请求最少

- 之后发送数据，会再次创建和所有broker的TCP连接
- connections.max.idle.ms参数设置多长时间没有流过此TCP连接，此链接就会被关闭，默认9min。设置为-1则关闭成为永久"长连接"（kafka会有探活机制keepalive）

### producer的缓存机制

- kafkaProduce会创建一个BufferPool，BufferPool再分割成一个一个的内存快，内存块的大小等于batch.size。通过将多个消息收集成一个batch，再把多个batch收集成一个request发送到broker来提高吞吐量。

- 关于内存分配问题详参：https://mp.weixin.qq.com/s/P6BO5KoMl_NQAI_OcwnXrQ

  [深度剖析 Kafka Producer 的缓冲池机制【图解 + 源码分析】](https://mp.weixin.qq.com/s/P6BO5KoMl_NQAI_OcwnXrQ)


```

```

## KafkaConsumer

- 