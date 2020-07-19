package com.wine.to.up.test.service.kafka;

public interface KafkaMessageHandler<Message> {

    void handle(Message message);
}
