package com.wine.to.up.demo.service.kafka;

public interface KafkaMessageHandler<Message> {

    void handle(Message message);
}
