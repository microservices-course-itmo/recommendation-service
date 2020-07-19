package com.wine.to.up.test.service.kafka;

import com.wine.to.up.test.service.annotations.InjectEventLogger;
import com.wine.to.up.test.service.logging.EventLogger;
import com.wine.to.up.test.service.logging.NotableEvents;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * BaseKafkaHandler subscribes consumer to a given topic and then polls the given {@link KafkaConsumer}
 * and delegates message to the underlying  {@link KafkaMessageHandler} for handling.
 * <p>
 * NOTE! As the handling is performed in {@link BaseKafkaHandler} execution thread, handling should not
 * be a very time-consuming operation. Otherwise, it should be performed in separate thread, but it can lead to
 * losing messages. {@see KafkaConsumer} at least once delivery semantics.
 *
 * @param <MessageType> - type of the message
 */
@Slf4j
public class BaseKafkaHandler<MessageType> {

    private final String topicName;
    private final KafkaConsumer<String, MessageType> kafkaConsumer;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final KafkaMessageHandler<MessageType> delegateTo;
    @InjectEventLogger
    @SuppressWarnings("unused")
    private EventLogger eventLogger;

    public BaseKafkaHandler(String topicName,
                            KafkaConsumer<String, MessageType> kafkaConsumer,
                            KafkaMessageHandler<MessageType> delegateTo) {
        this.topicName = topicName;
        this.kafkaConsumer = kafkaConsumer;
        this.delegateTo = delegateTo;
    }

    @PostConstruct
    void init() {
        kafkaConsumer.subscribe(Collections.singletonList(topicName));
        executor.submit(this::pollingMessages);
    }

    private void pollingMessages() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                ConsumerRecords<String, MessageType> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5)); // todo sukhoa add parameter poll interval?
                consumeMessages(consumerRecords);
            }
        } catch (Exception e) {
            eventLogger.error(NotableEvents.F_KAFKA_CONSUMER_DIED, topicName);
        } finally {
            close();
        }
    }

    private void consumeMessages(ConsumerRecords<String, MessageType> consumerRecords) {
        for (ConsumerRecord<String, MessageType> consumerRecord : consumerRecords) {
            log.debug("Received message: " + consumerRecord);
            try {
                delegateTo.handle(consumerRecord.value());
            } catch (Exception e) {
                log.warn("Error in kafka handler {}, message lost in topic {}",
                        delegateTo.getClass().getSimpleName(), topicName); // todo sukhoa event logger? or better just lost message counter?
            }
        }
    }

    public void close() { // todo sukhoa we should do it before shutting down the app
        kafkaConsumer.close();
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
        executor.shutdownNow();
    }
}
