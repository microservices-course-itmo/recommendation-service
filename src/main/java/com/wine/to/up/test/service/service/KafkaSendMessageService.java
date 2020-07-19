package com.wine.to.up.test.service.service;

import com.wine.to.up.test.service.annotations.InjectEventLogger;
import com.wine.to.up.test.service.components.AppMetrics;
import com.wine.to.up.test.service.logging.EventLogger;
import com.wine.to.up.test.service.logging.NotableEvents;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaSendMessageService {

    private final KafkaProducer<String, String> producer; // todo sukhoa investigate thread safety

    private AppMetrics appMetrics;

    @InjectEventLogger
    @SuppressWarnings("unused")
    private EventLogger eventLogger;

    @Autowired
    public KafkaSendMessageService(KafkaProducer<String, String> producer, AppMetrics appMetrics) {
        this.appMetrics = appMetrics;
        this.producer = producer;
    }

    public void sendMessage(String topicName, Headers headers, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
//        headers.forEach(header -> record.headers().add(header));
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                eventLogger.warn(NotableEvents.W_KAFKA_SEND_MESSAGE_FAILED, topicName);
                return;
            }
            log.debug("Message sent to Kafka topic: {}, message: {}", topicName, message);
            appMetrics.countKafkaMessageSent(topicName);
        });
    }
}
