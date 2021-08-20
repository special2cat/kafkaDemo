# TEST

## consumerTest

- 前提添加，两个线程消费同一个组内的同一个TOPIC(两个分区)

consumer配置：

```java
Properties props =  new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
//        props.put("group.id", GroupName.Group_Id_Simple_01);
        props.put("group.id", "testProcess002");
        props.put("session.timeout.ms", 30000);       // 如果其超时，将会可能触发rebalance并认为已经死去，重新选举Leader
        props.put("enable.auto.commit", "false");      // 开启自动提交
        props.put("auto.commit.interval.ms", "1000"); // 自动提交时间
        props.put("auto.offset.reset","earliest"); //earliest 从最早的offset开始拉取，latest:从最近的offset开始消费
        props.put("client.id", "producer-syn-1"); // 发送端id,便于统计
        props.put("max.poll.records","5"); // 每次批量拉取条数
        props.put("max.poll.interval.ms","1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("isolation.level","read_committed"); // 设置隔离级别
        return props;
```



```java
14:04:07.018 [Thread-0] INFO org.apache.kafka.clients.consumer.ConsumerConfig - ConsumerConfig values: 
	auto.commit.interval.ms = 1000
	auto.offset.reset = earliest
	bootstrap.servers = [127.0.0.1:9092]
	check.crcs = true
	client.id = producer-syn-1
	connections.max.idle.ms = 540000
	enable.auto.commit = false
	exclude.internal.topics = true
	fetch.max.bytes = 52428800
	fetch.max.wait.ms = 500
	fetch.min.bytes = 1
	group.id = testProcess002
	heartbeat.interval.ms = 3000
	interceptor.classes = null
	internal.leave.group.on.close = true
	isolation.level = read_committed
	key.deserializer = class org.apache.kafka.common.serialization.StringDeserializer
	max.partition.fetch.bytes = 1048576
	max.poll.interval.ms = 1000
	max.poll.records = 5
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor]
	receive.buffer.bytes = 65536
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 305000
	retry.backoff.ms = 100
	sasl.jaas.config = null
	sasl.kerberos.kinit.cmd = /usr/bin/kinit
	sasl.kerberos.min.time.before.relogin = 60000
	sasl.kerberos.service.name = null
	sasl.kerberos.ticket.renew.jitter = 0.05
	sasl.kerberos.ticket.renew.window.factor = 0.8
	sasl.mechanism = GSSAPI
	security.protocol = PLAINTEXT
	send.buffer.bytes = 131072
	session.timeout.ms = 30000
	ssl.cipher.suites = null
	ssl.enabled.protocols = [TLSv1.2, TLSv1.1, TLSv1]
	ssl.endpoint.identification.algorithm = null
	ssl.key.password = null
	ssl.keymanager.algorithm = SunX509
	ssl.keystore.location = null
	ssl.keystore.password = null
	ssl.keystore.type = JKS
	ssl.protocol = TLS
	ssl.provider = null
	ssl.secure.random.implementation = null
	ssl.trustmanager.algorithm = PKIX
	ssl.truststore.location = null
	ssl.truststore.password = null
	ssl.truststore.type = JKS
	value.deserializer = class org.apache.kafka.common.serialization.StringDeserializer

14:04:07.022 [Thread-0] DEBUG org.apache.kafka.clients.consumer.KafkaConsumer - Starting the Kafka consumer
14:04:07.022 [Thread-1] DEBUG org.apache.kafka.clients.consumer.KafkaConsumer - Starting the Kafka consumer
14:04:07.092 [Thread-0] DEBUG org.apache.kafka.clients.Metadata - Updated cluster metadata version 1 to Cluster(id = null, nodes = [127.0.0.1:9092 (id: -1 rack: null)], partitions = [])
14:04:07.092 [Thread-1] DEBUG org.apache.kafka.clients.Metadata - Updated cluster metadata version 1 to Cluster(id = null, nodes = [127.0.0.1:9092 (id: -1 rack: null)], partitions = [])
14:04:07.115 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name fetch-throttle-time
14:04:07.115 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name fetch-throttle-time
14:04:07.660 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name connections-closed:
14:04:07.660 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name connections-closed:
14:04:07.661 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name connections-created:
14:04:07.661 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name connections-created:
14:04:07.661 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-sent-received:
14:04:07.661 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-sent-received:
14:04:07.661 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-sent:
14:04:07.661 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-sent:
14:04:07.662 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-received:
14:04:07.662 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-received:
14:04:07.662 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name select-time:
14:04:07.662 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name select-time:
14:04:07.662 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name io-time:
14:04:07.662 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name io-time:
14:04:07.681 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name heartbeat-latency
14:04:07.682 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name join-latency
14:04:07.682 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name sync-latency
14:04:07.683 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name heartbeat-latency
14:04:07.684 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name join-latency
14:04:07.684 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name sync-latency
14:04:07.686 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name commit-latency
14:04:07.686 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name commit-latency
14:04:07.690 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-fetched
14:04:07.690 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name bytes-fetched
14:04:07.691 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name records-fetched
14:04:07.691 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name records-fetched
14:04:07.691 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name fetch-latency
14:04:07.691 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name fetch-latency
14:04:07.691 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name records-lag
14:04:07.691 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name records-lag
14:04:07.694 [Thread-1] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version : 0.11.0.1
14:04:07.694 [Thread-1] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId : c2a0d5f9b1f45bf5
14:04:07.695 [Thread-0] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version : 0.11.0.1
14:04:07.695 [Thread-0] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId : c2a0d5f9b1f45bf5
14:04:07.695 [Thread-1] DEBUG org.apache.kafka.clients.consumer.KafkaConsumer - Kafka consumer with client id producer-syn-1 created
14:04:07.696 [Thread-1] DEBUG org.apache.kafka.clients.consumer.KafkaConsumer - Subscribed to topic(s): TOPIC_SIMPLE_01
14:04:07.697 [Thread-0] WARN org.apache.kafka.common.utils.AppInfoParser - Error registering AppInfo mbean
javax.management.InstanceAlreadyExistsException: kafka.consumer:type=app-info,id=producer-syn-1
	at com.sun.jmx.mbeanserver.Repository.addMBean(Repository.java:437)
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.registerWithRepository(DefaultMBeanServerInterceptor.java:1898)
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.registerDynamicMBean(DefaultMBeanServerInterceptor.java:966)
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.registerObject(DefaultMBeanServerInterceptor.java:900)
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.registerMBean(DefaultMBeanServerInterceptor.java:324)
	at com.sun.jmx.mbeanserver.JmxMBeanServer.registerMBean(JmxMBeanServer.java:522)
	at org.apache.kafka.common.utils.AppInfoParser.registerAppInfo(AppInfoParser.java:58)
	at org.apache.kafka.clients.consumer.KafkaConsumer.<init>(KafkaConsumer.java:757)
	at org.apache.kafka.clients.consumer.KafkaConsumer.<init>(KafkaConsumer.java:633)
	at org.apache.kafka.clients.consumer.KafkaConsumer.<init>(KafkaConsumer.java:615)
	at com.example.demo.mybusiness.consumer.MyConsumer01$ThreadTask01.run(MyConsumer01.java:86)
	at java.lang.Thread.run(Thread.java:748)
14:04:07.697 [Thread-0] DEBUG org.apache.kafka.clients.consumer.KafkaConsumer - Kafka consumer with client id producer-syn-1 created
14:04:07.697 [Thread-0] DEBUG org.apache.kafka.clients.consumer.KafkaConsumer - Subscribed to topic(s): TOPIC_SIMPLE_01
Thread-1is countDown
Thread-0is countDown
14:04:09.979 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending GroupCoordinator request for group testProcess002 to broker 127.0.0.1:9092 (id: -1 rack: null)
14:04:09.979 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending GroupCoordinator request for group testProcess002 to broker 127.0.0.1:9092 (id: -1 rack: null)
14:04:09.986 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Initiating connection to node 127.0.0.1:9092 (id: -1 rack: null)
14:04:09.986 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Initiating connection to node 127.0.0.1:9092 (id: -1 rack: null)
14:04:09.992 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node--1.bytes-sent
14:04:09.992 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node--1.bytes-sent
14:04:09.992 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node--1.bytes-received
14:04:09.993 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node--1.latency
14:04:09.993 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node--1.bytes-received
14:04:09.993 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node--1.latency
14:04:09.993 [Thread-0] DEBUG org.apache.kafka.common.network.Selector - Created socket with SO_RCVBUF = 65536, SO_SNDBUF = 131072, SO_TIMEOUT = 0 to node -1
14:04:09.993 [Thread-1] DEBUG org.apache.kafka.common.network.Selector - Created socket with SO_RCVBUF = 65536, SO_SNDBUF = 131072, SO_TIMEOUT = 0 to node -1
14:04:09.994 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Completed connection to node -1. Fetching API versions.
14:04:09.994 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Initiating API versions fetch from node -1.
14:04:09.994 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Completed connection to node -1. Fetching API versions.
14:04:09.994 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Initiating API versions fetch from node -1.
14:04:10.076 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Recorded API versions for node -1: (Produce(0): 0 to 9 [usable: 3], Fetch(1): 0 to 12 [usable: 5], Offsets(2): 0 to 6 [usable: 2], Metadata(3): 0 to 11 [usable: 4], LeaderAndIsr(4): 0 to 5 [usable: 0], StopReplica(5): 0 to 3 [usable: 0], UpdateMetadata(6): 0 to 7 [usable: 3], ControlledShutdown(7): 0 to 3 [usable: 1], OffsetCommit(8): 0 to 8 [usable: 3], OffsetFetch(9): 0 to 7 [usable: 3], FindCoordinator(10): 0 to 3 [usable: 1], JoinGroup(11): 0 to 7 [usable: 2], Heartbeat(12): 0 to 4 [usable: 1], LeaveGroup(13): 0 to 4 [usable: 1], SyncGroup(14): 0 to 5 [usable: 1], DescribeGroups(15): 0 to 5 [usable: 1], ListGroups(16): 0 to 4 [usable: 1], SaslHandshake(17): 0 to 1 [usable: 0], ApiVersions(18): 0 to 3 [usable: 1], CreateTopics(19): 0 to 7 [usable: 2], DeleteTopics(20): 0 to 6 [usable: 1], DeleteRecords(21): 0 to 2 [usable: 0], InitProducerId(22): 0 to 4 [usable: 0], OffsetForLeaderEpoch(23): 0 to 4 [usable: 0], AddPartitionsToTxn(24): 0 to 3 [usable: 0], AddOffsetsToTxn(25): 0 to 3 [usable: 0], EndTxn(26): 0 to 3 [usable: 0], WriteTxnMarkers(27): 0 to 1 [usable: 0], TxnOffsetCommit(28): 0 to 3 [usable: 0], DescribeAcls(29): 0 to 2 [usable: 0], CreateAcls(30): 0 to 2 [usable: 0], DeleteAcls(31): 0 to 2 [usable: 0], DescribeConfigs(32): 0 to 4 [usable: 0], AlterConfigs(33): 0 to 2 [usable: 0], UNKNOWN(34): 0 to 2, UNKNOWN(35): 0 to 2, UNKNOWN(36): 0 to 2, UNKNOWN(37): 0 to 3, UNKNOWN(38): 0 to 2, UNKNOWN(39): 0 to 2, UNKNOWN(40): 0 to 2, UNKNOWN(41): 0 to 2, UNKNOWN(42): 0 to 2, UNKNOWN(43): 0 to 2, UNKNOWN(44): 0 to 1, UNKNOWN(45): 0, UNKNOWN(46): 0, UNKNOWN(47): 0, UNKNOWN(48): 0 to 1, UNKNOWN(49): 0 to 1, UNKNOWN(50): 0, UNKNOWN(51): 0, UNKNOWN(56): 0, UNKNOWN(57): 0, UNKNOWN(60): 0, UNKNOWN(61): 0)
14:04:10.076 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Recorded API versions for node -1: (Produce(0): 0 to 9 [usable: 3], Fetch(1): 0 to 12 [usable: 5], Offsets(2): 0 to 6 [usable: 2], Metadata(3): 0 to 11 [usable: 4], LeaderAndIsr(4): 0 to 5 [usable: 0], StopReplica(5): 0 to 3 [usable: 0], UpdateMetadata(6): 0 to 7 [usable: 3], ControlledShutdown(7): 0 to 3 [usable: 1], OffsetCommit(8): 0 to 8 [usable: 3], OffsetFetch(9): 0 to 7 [usable: 3], FindCoordinator(10): 0 to 3 [usable: 1], JoinGroup(11): 0 to 7 [usable: 2], Heartbeat(12): 0 to 4 [usable: 1], LeaveGroup(13): 0 to 4 [usable: 1], SyncGroup(14): 0 to 5 [usable: 1], DescribeGroups(15): 0 to 5 [usable: 1], ListGroups(16): 0 to 4 [usable: 1], SaslHandshake(17): 0 to 1 [usable: 0], ApiVersions(18): 0 to 3 [usable: 1], CreateTopics(19): 0 to 7 [usable: 2], DeleteTopics(20): 0 to 6 [usable: 1], DeleteRecords(21): 0 to 2 [usable: 0], InitProducerId(22): 0 to 4 [usable: 0], OffsetForLeaderEpoch(23): 0 to 4 [usable: 0], AddPartitionsToTxn(24): 0 to 3 [usable: 0], AddOffsetsToTxn(25): 0 to 3 [usable: 0], EndTxn(26): 0 to 3 [usable: 0], WriteTxnMarkers(27): 0 to 1 [usable: 0], TxnOffsetCommit(28): 0 to 3 [usable: 0], DescribeAcls(29): 0 to 2 [usable: 0], CreateAcls(30): 0 to 2 [usable: 0], DeleteAcls(31): 0 to 2 [usable: 0], DescribeConfigs(32): 0 to 4 [usable: 0], AlterConfigs(33): 0 to 2 [usable: 0], UNKNOWN(34): 0 to 2, UNKNOWN(35): 0 to 2, UNKNOWN(36): 0 to 2, UNKNOWN(37): 0 to 3, UNKNOWN(38): 0 to 2, UNKNOWN(39): 0 to 2, UNKNOWN(40): 0 to 2, UNKNOWN(41): 0 to 2, UNKNOWN(42): 0 to 2, UNKNOWN(43): 0 to 2, UNKNOWN(44): 0 to 1, UNKNOWN(45): 0, UNKNOWN(46): 0, UNKNOWN(47): 0, UNKNOWN(48): 0 to 1, UNKNOWN(49): 0 to 1, UNKNOWN(50): 0, UNKNOWN(51): 0, UNKNOWN(56): 0, UNKNOWN(57): 0, UNKNOWN(60): 0, UNKNOWN(61): 0)
14:04:10.077 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Sending metadata request (type=MetadataRequest, topics=TOPIC_SIMPLE_01) to node 127.0.0.1:9092 (id: -1 rack: null)
14:04:10.077 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Sending metadata request (type=MetadataRequest, topics=TOPIC_SIMPLE_01) to node 127.0.0.1:9092 (id: -1 rack: null)
14:04:10.081 [Thread-1] DEBUG org.apache.kafka.clients.Metadata - Updated cluster metadata version 2 to Cluster(id = KvOmPTxbR7yhAgOcSL584Q, nodes = [TY-202107081225:9092 (id: 0 rack: null)], partitions = [Partition(topic = TOPIC_SIMPLE_01, partition = 0, leader = 0, replicas = [0], isr = [0]), Partition(topic = TOPIC_SIMPLE_01, partition = 1, leader = 0, replicas = [0], isr = [0])])
14:04:10.081 [Thread-0] DEBUG org.apache.kafka.clients.Metadata - Updated cluster metadata version 2 to Cluster(id = KvOmPTxbR7yhAgOcSL584Q, nodes = [TY-202107081225:9092 (id: 0 rack: null)], partitions = [Partition(topic = TOPIC_SIMPLE_01, partition = 0, leader = 0, replicas = [0], isr = [0]), Partition(topic = TOPIC_SIMPLE_01, partition = 1, leader = 0, replicas = [0], isr = [0])])
14:04:10.084 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Received GroupCoordinator response ClientResponse(receivedTimeMs=1629439450083, latencyMs=101, disconnected=false, requestHeader={api_key=10,api_version=1,correlation_id=0,client_id=producer-syn-1}, responseBody=FindCoordinatorResponse(throttleTimeMs=0, errorMessage='NONE', error=NONE, node=TY-202107081225:9092 (id: 0 rack: null))) for group testProcess002
14:04:10.084 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Received GroupCoordinator response ClientResponse(receivedTimeMs=1629439450083, latencyMs=101, disconnected=false, requestHeader={api_key=10,api_version=1,correlation_id=0,client_id=producer-syn-1}, responseBody=FindCoordinatorResponse(throttleTimeMs=0, errorMessage='NONE', error=NONE, node=TY-202107081225:9092 (id: 0 rack: null))) for group testProcess002
14:04:10.084 [Thread-1] INFO org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Discovered coordinator TY-202107081225:9092 (id: 2147483647 rack: null) for group testProcess002.
14:04:10.084 [Thread-0] INFO org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Discovered coordinator TY-202107081225:9092 (id: 2147483647 rack: null) for group testProcess002.
14:04:10.084 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Initiating connection to node TY-202107081225:9092 (id: 2147483647 rack: null)
14:04:10.084 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Initiating connection to node TY-202107081225:9092 (id: 2147483647 rack: null)
14:04:10.089 [Thread-0] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Revoking previously assigned partitions [] for group testProcess002
14:04:10.089 [Thread-1] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Revoking previously assigned partitions [] for group testProcess002
14:04:10.090 [Thread-0] INFO org.apache.kafka.clients.consumer.internals.AbstractCoordinator - (Re-)joining group testProcess002
14:04:10.090 [Thread-1] INFO org.apache.kafka.clients.consumer.internals.AbstractCoordinator - (Re-)joining group testProcess002
14:04:10.090 [kafka-coordinator-heartbeat-thread | testProcess002] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Heartbeat thread for group testProcess002 started
14:04:10.090 [kafka-coordinator-heartbeat-thread | testProcess002] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Heartbeat thread for group testProcess002 started
14:04:10.091 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending JoinGroup ((type: JoinGroupRequest, groupId=testProcess002, sessionTimeout=30000, rebalanceTimeout=1000, memberId=, protocolType=consumer, groupProtocols=org.apache.kafka.common.requests.JoinGroupRequest$ProtocolMetadata@58ec86e4)) to coordinator TY-202107081225:9092 (id: 2147483647 rack: null)
14:04:10.091 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending JoinGroup ((type: JoinGroupRequest, groupId=testProcess002, sessionTimeout=30000, rebalanceTimeout=1000, memberId=, protocolType=consumer, groupProtocols=org.apache.kafka.common.requests.JoinGroupRequest$ProtocolMetadata@52ba549c)) to coordinator TY-202107081225:9092 (id: 2147483647 rack: null)
14:04:10.092 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-2147483647.bytes-sent
14:04:10.092 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-2147483647.bytes-sent
14:04:10.093 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-2147483647.bytes-received
14:04:10.093 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-2147483647.bytes-received
14:04:10.094 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-2147483647.latency
14:04:10.094 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-2147483647.latency
14:04:10.094 [Thread-0] DEBUG org.apache.kafka.common.network.Selector - Created socket with SO_RCVBUF = 65536, SO_SNDBUF = 131072, SO_TIMEOUT = 0 to node 2147483647
14:04:10.094 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Completed connection to node 2147483647. Fetching API versions.
14:04:10.094 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Initiating API versions fetch from node 2147483647.
14:04:10.094 [Thread-1] DEBUG org.apache.kafka.common.network.Selector - Created socket with SO_RCVBUF = 65536, SO_SNDBUF = 131072, SO_TIMEOUT = 0 to node 2147483647
14:04:10.094 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Completed connection to node 2147483647. Fetching API versions.
14:04:10.094 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Initiating API versions fetch from node 2147483647.
14:04:10.096 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Recorded API versions for node 2147483647: (Produce(0): 0 to 9 [usable: 3], Fetch(1): 0 to 12 [usable: 5], Offsets(2): 0 to 6 [usable: 2], Metadata(3): 0 to 11 [usable: 4], LeaderAndIsr(4): 0 to 5 [usable: 0], StopReplica(5): 0 to 3 [usable: 0], UpdateMetadata(6): 0 to 7 [usable: 3], ControlledShutdown(7): 0 to 3 [usable: 1], OffsetCommit(8): 0 to 8 [usable: 3], OffsetFetch(9): 0 to 7 [usable: 3], FindCoordinator(10): 0 to 3 [usable: 1], JoinGroup(11): 0 to 7 [usable: 2], Heartbeat(12): 0 to 4 [usable: 1], LeaveGroup(13): 0 to 4 [usable: 1], SyncGroup(14): 0 to 5 [usable: 1], DescribeGroups(15): 0 to 5 [usable: 1], ListGroups(16): 0 to 4 [usable: 1], SaslHandshake(17): 0 to 1 [usable: 0], ApiVersions(18): 0 to 3 [usable: 1], CreateTopics(19): 0 to 7 [usable: 2], DeleteTopics(20): 0 to 6 [usable: 1], DeleteRecords(21): 0 to 2 [usable: 0], InitProducerId(22): 0 to 4 [usable: 0], OffsetForLeaderEpoch(23): 0 to 4 [usable: 0], AddPartitionsToTxn(24): 0 to 3 [usable: 0], AddOffsetsToTxn(25): 0 to 3 [usable: 0], EndTxn(26): 0 to 3 [usable: 0], WriteTxnMarkers(27): 0 to 1 [usable: 0], TxnOffsetCommit(28): 0 to 3 [usable: 0], DescribeAcls(29): 0 to 2 [usable: 0], CreateAcls(30): 0 to 2 [usable: 0], DeleteAcls(31): 0 to 2 [usable: 0], DescribeConfigs(32): 0 to 4 [usable: 0], AlterConfigs(33): 0 to 2 [usable: 0], UNKNOWN(34): 0 to 2, UNKNOWN(35): 0 to 2, UNKNOWN(36): 0 to 2, UNKNOWN(37): 0 to 3, UNKNOWN(38): 0 to 2, UNKNOWN(39): 0 to 2, UNKNOWN(40): 0 to 2, UNKNOWN(41): 0 to 2, UNKNOWN(42): 0 to 2, UNKNOWN(43): 0 to 2, UNKNOWN(44): 0 to 1, UNKNOWN(45): 0, UNKNOWN(46): 0, UNKNOWN(47): 0, UNKNOWN(48): 0 to 1, UNKNOWN(49): 0 to 1, UNKNOWN(50): 0, UNKNOWN(51): 0, UNKNOWN(56): 0, UNKNOWN(57): 0, UNKNOWN(60): 0, UNKNOWN(61): 0)
14:04:10.096 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Recorded API versions for node 2147483647: (Produce(0): 0 to 9 [usable: 3], Fetch(1): 0 to 12 [usable: 5], Offsets(2): 0 to 6 [usable: 2], Metadata(3): 0 to 11 [usable: 4], LeaderAndIsr(4): 0 to 5 [usable: 0], StopReplica(5): 0 to 3 [usable: 0], UpdateMetadata(6): 0 to 7 [usable: 3], ControlledShutdown(7): 0 to 3 [usable: 1], OffsetCommit(8): 0 to 8 [usable: 3], OffsetFetch(9): 0 to 7 [usable: 3], FindCoordinator(10): 0 to 3 [usable: 1], JoinGroup(11): 0 to 7 [usable: 2], Heartbeat(12): 0 to 4 [usable: 1], LeaveGroup(13): 0 to 4 [usable: 1], SyncGroup(14): 0 to 5 [usable: 1], DescribeGroups(15): 0 to 5 [usable: 1], ListGroups(16): 0 to 4 [usable: 1], SaslHandshake(17): 0 to 1 [usable: 0], ApiVersions(18): 0 to 3 [usable: 1], CreateTopics(19): 0 to 7 [usable: 2], DeleteTopics(20): 0 to 6 [usable: 1], DeleteRecords(21): 0 to 2 [usable: 0], InitProducerId(22): 0 to 4 [usable: 0], OffsetForLeaderEpoch(23): 0 to 4 [usable: 0], AddPartitionsToTxn(24): 0 to 3 [usable: 0], AddOffsetsToTxn(25): 0 to 3 [usable: 0], EndTxn(26): 0 to 3 [usable: 0], WriteTxnMarkers(27): 0 to 1 [usable: 0], TxnOffsetCommit(28): 0 to 3 [usable: 0], DescribeAcls(29): 0 to 2 [usable: 0], CreateAcls(30): 0 to 2 [usable: 0], DeleteAcls(31): 0 to 2 [usable: 0], DescribeConfigs(32): 0 to 4 [usable: 0], AlterConfigs(33): 0 to 2 [usable: 0], UNKNOWN(34): 0 to 2, UNKNOWN(35): 0 to 2, UNKNOWN(36): 0 to 2, UNKNOWN(37): 0 to 3, UNKNOWN(38): 0 to 2, UNKNOWN(39): 0 to 2, UNKNOWN(40): 0 to 2, UNKNOWN(41): 0 to 2, UNKNOWN(42): 0 to 2, UNKNOWN(43): 0 to 2, UNKNOWN(44): 0 to 1, UNKNOWN(45): 0, UNKNOWN(46): 0, UNKNOWN(47): 0, UNKNOWN(48): 0 to 1, UNKNOWN(49): 0 to 1, UNKNOWN(50): 0, UNKNOWN(51): 0, UNKNOWN(56): 0, UNKNOWN(57): 0, UNKNOWN(60): 0, UNKNOWN(61): 0)
14:04:10.106 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Received successful JoinGroup response for group testProcess002: org.apache.kafka.common.requests.JoinGroupResponse@21b9de84
14:04:10.106 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Received successful JoinGroup response for group testProcess002: org.apache.kafka.common.requests.JoinGroupResponse@49e45ef
14:04:10.106 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending follower SyncGroup for group testProcess002 to coordinator TY-202107081225:9092 (id: 2147483647 rack: null): (type=SyncGroupRequest, groupId=testProcess002, generationId=7, memberId=producer-syn-1-ee8404d5-76a7-4b14-a154-db2e2d6a1191, groupAssignment=)
14:04:10.107 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Performing assignment for group testProcess002 using strategy range with subscriptions {producer-syn-1-ee8404d5-76a7-4b14-a154-db2e2d6a1191=Subscription(topics=[TOPIC_SIMPLE_01]), producer-syn-1-0ebece29-9cd9-484b-87d8-83bee0e7d995=Subscription(topics=[TOPIC_SIMPLE_01])}
14:04:10.108 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Finished assignment for group testProcess002: {producer-syn-1-ee8404d5-76a7-4b14-a154-db2e2d6a1191=Assignment(partitions=[TOPIC_SIMPLE_01-1]), producer-syn-1-0ebece29-9cd9-484b-87d8-83bee0e7d995=Assignment(partitions=[TOPIC_SIMPLE_01-0])}
14:04:10.109 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending leader SyncGroup for group testProcess002 to coordinator TY-202107081225:9092 (id: 2147483647 rack: null): (type=SyncGroupRequest, groupId=testProcess002, generationId=7, memberId=producer-syn-1-0ebece29-9cd9-484b-87d8-83bee0e7d995, groupAssignment=producer-syn-1-ee8404d5-76a7-4b14-a154-db2e2d6a1191,producer-syn-1-0ebece29-9cd9-484b-87d8-83bee0e7d995)
14:04:10.113 [Thread-0] INFO org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Successfully joined group testProcess002 with generation 7
14:04:10.113 [Thread-1] INFO org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Successfully joined group testProcess002 with generation 7
14:04:10.114 [Thread-0] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Setting newly assigned partitions [TOPIC_SIMPLE_01-0] for group testProcess002
14:04:10.114 [Thread-1] INFO org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Setting newly assigned partitions [TOPIC_SIMPLE_01-1] for group testProcess002
14:04:10.114 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Group testProcess002 fetching committed offsets for partitions: [TOPIC_SIMPLE_01-0]
14:04:10.114 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Group testProcess002 fetching committed offsets for partitions: [TOPIC_SIMPLE_01-1]
14:04:10.117 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Group testProcess002 has no committed offset for partition TOPIC_SIMPLE_01-1
14:04:10.117 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.ConsumerCoordinator - Group testProcess002 has no committed offset for partition TOPIC_SIMPLE_01-0
14:04:10.117 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Initiating connection to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.117 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Initiating connection to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.118 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-0.bytes-sent
14:04:10.118 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-0.bytes-sent
14:04:10.119 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-0.bytes-received
14:04:10.119 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-0.bytes-received
14:04:10.119 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-0.latency
14:04:10.119 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name node-0.latency
14:04:10.119 [Thread-1] DEBUG org.apache.kafka.common.network.Selector - Created socket with SO_RCVBUF = 65536, SO_SNDBUF = 131072, SO_TIMEOUT = 0 to node 0
14:04:10.119 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Completed connection to node 0. Fetching API versions.
14:04:10.119 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Initiating API versions fetch from node 0.
14:04:10.120 [Thread-0] DEBUG org.apache.kafka.common.network.Selector - Created socket with SO_RCVBUF = 65536, SO_SNDBUF = 131072, SO_TIMEOUT = 0 to node 0
14:04:10.120 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Completed connection to node 0. Fetching API versions.
14:04:10.120 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Initiating API versions fetch from node 0.
14:04:10.120 [Thread-1] DEBUG org.apache.kafka.clients.NetworkClient - Recorded API versions for node 0: (Produce(0): 0 to 9 [usable: 3], Fetch(1): 0 to 12 [usable: 5], Offsets(2): 0 to 6 [usable: 2], Metadata(3): 0 to 11 [usable: 4], LeaderAndIsr(4): 0 to 5 [usable: 0], StopReplica(5): 0 to 3 [usable: 0], UpdateMetadata(6): 0 to 7 [usable: 3], ControlledShutdown(7): 0 to 3 [usable: 1], OffsetCommit(8): 0 to 8 [usable: 3], OffsetFetch(9): 0 to 7 [usable: 3], FindCoordinator(10): 0 to 3 [usable: 1], JoinGroup(11): 0 to 7 [usable: 2], Heartbeat(12): 0 to 4 [usable: 1], LeaveGroup(13): 0 to 4 [usable: 1], SyncGroup(14): 0 to 5 [usable: 1], DescribeGroups(15): 0 to 5 [usable: 1], ListGroups(16): 0 to 4 [usable: 1], SaslHandshake(17): 0 to 1 [usable: 0], ApiVersions(18): 0 to 3 [usable: 1], CreateTopics(19): 0 to 7 [usable: 2], DeleteTopics(20): 0 to 6 [usable: 1], DeleteRecords(21): 0 to 2 [usable: 0], InitProducerId(22): 0 to 4 [usable: 0], OffsetForLeaderEpoch(23): 0 to 4 [usable: 0], AddPartitionsToTxn(24): 0 to 3 [usable: 0], AddOffsetsToTxn(25): 0 to 3 [usable: 0], EndTxn(26): 0 to 3 [usable: 0], WriteTxnMarkers(27): 0 to 1 [usable: 0], TxnOffsetCommit(28): 0 to 3 [usable: 0], DescribeAcls(29): 0 to 2 [usable: 0], CreateAcls(30): 0 to 2 [usable: 0], DeleteAcls(31): 0 to 2 [usable: 0], DescribeConfigs(32): 0 to 4 [usable: 0], AlterConfigs(33): 0 to 2 [usable: 0], UNKNOWN(34): 0 to 2, UNKNOWN(35): 0 to 2, UNKNOWN(36): 0 to 2, UNKNOWN(37): 0 to 3, UNKNOWN(38): 0 to 2, UNKNOWN(39): 0 to 2, UNKNOWN(40): 0 to 2, UNKNOWN(41): 0 to 2, UNKNOWN(42): 0 to 2, UNKNOWN(43): 0 to 2, UNKNOWN(44): 0 to 1, UNKNOWN(45): 0, UNKNOWN(46): 0, UNKNOWN(47): 0, UNKNOWN(48): 0 to 1, UNKNOWN(49): 0 to 1, UNKNOWN(50): 0, UNKNOWN(51): 0, UNKNOWN(56): 0, UNKNOWN(57): 0, UNKNOWN(60): 0, UNKNOWN(61): 0)
14:04:10.121 [Thread-0] DEBUG org.apache.kafka.clients.NetworkClient - Recorded API versions for node 0: (Produce(0): 0 to 9 [usable: 3], Fetch(1): 0 to 12 [usable: 5], Offsets(2): 0 to 6 [usable: 2], Metadata(3): 0 to 11 [usable: 4], LeaderAndIsr(4): 0 to 5 [usable: 0], StopReplica(5): 0 to 3 [usable: 0], UpdateMetadata(6): 0 to 7 [usable: 3], ControlledShutdown(7): 0 to 3 [usable: 1], OffsetCommit(8): 0 to 8 [usable: 3], OffsetFetch(9): 0 to 7 [usable: 3], FindCoordinator(10): 0 to 3 [usable: 1], JoinGroup(11): 0 to 7 [usable: 2], Heartbeat(12): 0 to 4 [usable: 1], LeaveGroup(13): 0 to 4 [usable: 1], SyncGroup(14): 0 to 5 [usable: 1], DescribeGroups(15): 0 to 5 [usable: 1], ListGroups(16): 0 to 4 [usable: 1], SaslHandshake(17): 0 to 1 [usable: 0], ApiVersions(18): 0 to 3 [usable: 1], CreateTopics(19): 0 to 7 [usable: 2], DeleteTopics(20): 0 to 6 [usable: 1], DeleteRecords(21): 0 to 2 [usable: 0], InitProducerId(22): 0 to 4 [usable: 0], OffsetForLeaderEpoch(23): 0 to 4 [usable: 0], AddPartitionsToTxn(24): 0 to 3 [usable: 0], AddOffsetsToTxn(25): 0 to 3 [usable: 0], EndTxn(26): 0 to 3 [usable: 0], WriteTxnMarkers(27): 0 to 1 [usable: 0], TxnOffsetCommit(28): 0 to 3 [usable: 0], DescribeAcls(29): 0 to 2 [usable: 0], CreateAcls(30): 0 to 2 [usable: 0], DeleteAcls(31): 0 to 2 [usable: 0], DescribeConfigs(32): 0 to 4 [usable: 0], AlterConfigs(33): 0 to 2 [usable: 0], UNKNOWN(34): 0 to 2, UNKNOWN(35): 0 to 2, UNKNOWN(36): 0 to 2, UNKNOWN(37): 0 to 3, UNKNOWN(38): 0 to 2, UNKNOWN(39): 0 to 2, UNKNOWN(40): 0 to 2, UNKNOWN(41): 0 to 2, UNKNOWN(42): 0 to 2, UNKNOWN(43): 0 to 2, UNKNOWN(44): 0 to 1, UNKNOWN(45): 0, UNKNOWN(46): 0, UNKNOWN(47): 0, UNKNOWN(48): 0 to 1, UNKNOWN(49): 0 to 1, UNKNOWN(50): 0, UNKNOWN(51): 0, UNKNOWN(56): 0, UNKNOWN(57): 0, UNKNOWN(60): 0, UNKNOWN(61): 0)
14:04:10.123 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Handling ListOffsetResponse response for TOPIC_SIMPLE_01-1. Fetched offset 0, timestamp -1
14:04:10.123 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Handling ListOffsetResponse response for TOPIC_SIMPLE_01-0. Fetched offset 0, timestamp -1
14:04:10.123 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Resetting offset for partition TOPIC_SIMPLE_01-1 to offset 0.
14:04:10.123 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Resetting offset for partition TOPIC_SIMPLE_01-0 to offset 0.
14:04:10.124 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 0 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.125 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:10.126 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 0 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.126 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:10.135 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 0 for partition TOPIC_SIMPLE_01-0 returned fetch data (error=NONE, highWaterMark=36, lastStableOffset = 36, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=1158)
14:04:10.135 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 0 for partition TOPIC_SIMPLE_01-1 returned fetch data (error=NONE, highWaterMark=24, lastStableOffset = 24, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=745)
14:04:10.148 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name TOPIC_SIMPLE_01-0.records-lag
14:04:10.148 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name TOPIC_SIMPLE_01-1.records-lag
topic:TOPIC_SIMPLE_01,offset:0 ,key:topic_0   ,value:producer-asyn-0   partition:0    timestamp1629361500737
topic:TOPIC_SIMPLE_01,offset:0 ,key:1   ,value:only key and value1   partition:1    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:1 ,key:topic_1   ,value:producer-asyn-1   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:1 ,key:3   ,value:only key and value3   partition:1    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:2 ,key:topic_2   ,value:producer-asyn-2   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:2 ,key:4   ,value:only key and value4   partition:1    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:3 ,key:topic_3   ,value:producer-asyn-3   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:3 ,key:7   ,value:only key and value7   partition:1    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:4 ,key:topic_4   ,value:producer-asyn-4   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:4 ,key:8   ,value:only key and value8   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:5 ,key:topic_5   ,value:producer-asyn-5   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:6 ,key:topic_6   ,value:producer-asyn-6   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:7 ,key:topic_7   ,value:producer-asyn-7   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:8 ,key:topic_8   ,value:producer-asyn-8   partition:0    timestamp1629361500743
topic:TOPIC_SIMPLE_01,offset:9 ,key:topic_9   ,value:producer-asyn-9   partition:0    timestamp1629361500744
topic:TOPIC_SIMPLE_01,offset:5 ,key:9   ,value:only key and value9   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:6 ,key:13   ,value:only key and value13   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:7 ,key:14   ,value:only key and value14   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:8 ,key:16   ,value:only key and value16   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:9 ,key:19   ,value:only key and value19   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:10 ,key:0   ,value:only key and value0   partition:0    timestamp1629363015051
topic:TOPIC_SIMPLE_01,offset:11 ,key:2   ,value:only key and value2   partition:0    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:12 ,key:5   ,value:only key and value5   partition:0    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:13 ,key:6   ,value:only key and value6   partition:0    timestamp1629363015059
topic:TOPIC_SIMPLE_01,offset:14 ,key:10   ,value:only key and value10   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:10 ,key:22   ,value:only key and value22   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:11 ,key:24   ,value:only key and value24   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:12 ,key:25   ,value:only key and value25   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:13 ,key:28   ,value:only key and value28   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:15 ,key:11   ,value:only key and value11   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:16 ,key:12   ,value:only key and value12   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:17 ,key:15   ,value:only key and value15   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:18 ,key:17   ,value:only key and value17   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:19 ,key:18   ,value:only key and value18   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:14 ,key:29   ,value:only key and value29   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:20 ,key:20   ,value:only key and value20   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:21 ,key:21   ,value:only key and value21   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:22 ,key:23   ,value:only key and value23   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:15 ,key:30   ,value:only key and value30   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:23 ,key:26   ,value:only key and value26   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:16 ,key:31   ,value:only key and value31   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:24 ,key:27   ,value:only key and value27   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:17 ,key:32   ,value:only key and value32   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:18 ,key:33   ,value:only key and value33   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:19 ,key:35   ,value:only key and value35   partition:1    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:25 ,key:34   ,value:only key and value34   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:26 ,key:36   ,value:only key and value36   partition:0    timestamp1629363015060
topic:TOPIC_SIMPLE_01,offset:27 ,key:37   ,value:only key and value37   partition:0    timestamp1629363015061
topic:TOPIC_SIMPLE_01,offset:28 ,key:38   ,value:only key and value38   partition:0    timestamp1629363015061
topic:TOPIC_SIMPLE_01,offset:29 ,key:41   ,value:only key and value41   partition:0    timestamp1629363015061
14:04:10.151 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name topic.TOPIC_SIMPLE_01.bytes-fetched
topic:TOPIC_SIMPLE_01,offset:30 ,key:42   ,value:only key and value42   partition:0    timestamp1629363015061
topic:TOPIC_SIMPLE_01,offset:31 ,key:43   ,value:only key and value43   partition:0    timestamp1629363015062
topic:TOPIC_SIMPLE_01,offset:32 ,key:45   ,value:only key and value45   partition:0    timestamp1629363015062
topic:TOPIC_SIMPLE_01,offset:33 ,key:47   ,value:only key and value47   partition:0    timestamp1629363015062
topic:TOPIC_SIMPLE_01,offset:34 ,key:48   ,value:only key and value48   partition:0    timestamp1629363015062
14:04:10.152 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name topic.TOPIC_SIMPLE_01.bytes-fetched
14:04:10.152 [Thread-1] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name topic.TOPIC_SIMPLE_01.records-fetched
14:04:10.152 [Thread-0] DEBUG org.apache.kafka.common.metrics.Metrics - Added sensor with name topic.TOPIC_SIMPLE_01.records-fetched
14:04:10.152 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 24 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.152 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 36 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.152 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:10.152 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
topic:TOPIC_SIMPLE_01,offset:20 ,key:39   ,value:only key and value39   partition:1    timestamp1629363015061
topic:TOPIC_SIMPLE_01,offset:21 ,key:40   ,value:only key and value40   partition:1    timestamp1629363015061
topic:TOPIC_SIMPLE_01,offset:22 ,key:44   ,value:only key and value44   partition:1    timestamp1629363015062
topic:TOPIC_SIMPLE_01,offset:23 ,key:46   ,value:only key and value46   partition:1    timestamp1629363015062
topic:TOPIC_SIMPLE_01,offset:35 ,key:49   ,value:only key and value49   partition:0    timestamp1629363015062
14:04:10.655 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 24 for partition TOPIC_SIMPLE_01-1 returned fetch data (error=NONE, highWaterMark=24, lastStableOffset = 24, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:10.655 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 36 for partition TOPIC_SIMPLE_01-0 returned fetch data (error=NONE, highWaterMark=36, lastStableOffset = 36, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:10.656 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 36 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.656 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:10.657 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 24 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:10.657 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:11.159 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 36 for partition TOPIC_SIMPLE_01-0 returned fetch data (error=NONE, highWaterMark=36, lastStableOffset = 36, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:11.159 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 24 for partition TOPIC_SIMPLE_01-1 returned fetch data (error=NONE, highWaterMark=24, lastStableOffset = 24, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:11.159 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 36 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:11.159 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:11.159 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 24 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:11.159 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:11.661 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 24 for partition TOPIC_SIMPLE_01-1 returned fetch data (error=NONE, highWaterMark=24, lastStableOffset = 24, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:11.661 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 36 for partition TOPIC_SIMPLE_01-0 returned fetch data (error=NONE, highWaterMark=36, lastStableOffset = 36, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:11.662 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 24 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:11.662 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:11.662 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 36 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:11.662 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:12.164 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 24 for partition TOPIC_SIMPLE_01-1 returned fetch data (error=NONE, highWaterMark=24, lastStableOffset = 24, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:12.164 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 36 for partition TOPIC_SIMPLE_01-0 returned fetch data (error=NONE, highWaterMark=36, lastStableOffset = 36, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:12.165 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 36 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:12.165 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 24 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:12.165 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:12.165 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:12.666 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 24 for partition TOPIC_SIMPLE_01-1 returned fetch data (error=NONE, highWaterMark=24, lastStableOffset = 24, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:12.666 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Fetch READ_COMMITTED at offset 36 for partition TOPIC_SIMPLE_01-0 returned fetch data (error=NONE, highWaterMark=36, lastStableOffset = 36, logStartOffset = 0, abortedTransactions = [], recordsSizeInBytes=0)
14:04:12.666 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-1 at offset 24 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:12.666 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-1] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:12.666 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Added READ_COMMITTED fetch request for partition TOPIC_SIMPLE_01-0 at offset 36 to node TY-202107081225:9092 (id: 0 rack: null)
14:04:12.666 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.Fetcher - Sending READ_COMMITTED fetch for partitions [TOPIC_SIMPLE_01-0] to broker TY-202107081225:9092 (id: 0 rack: null)
14:04:13.114 [kafka-coordinator-heartbeat-thread | testProcess002] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending Heartbeat request for group testProcess002 to coordinator TY-202107081225:9092 (id: 2147483647 rack: null)
14:04:13.114 [kafka-coordinator-heartbeat-thread | testProcess002] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Sending Heartbeat request for group testProcess002 to coordinator TY-202107081225:9092 (id: 2147483647 rack: null)
14:04:13.116 [Thread-1] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Received successful Heartbeat response for group testProcess002
14:04:13.116 [Thread-0] DEBUG org.apache.kafka.clients.consumer.internals.AbstractCoordinator - Received successful Heartbeat response for group testProcess002
```

