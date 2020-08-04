package com.wine.to.up.test.service.logging;

public enum NotableEvents {
    DEFAULT("Default event"),
    EXCEPTION("Some exception occurred"),

    W_KAFKA_SEND_MESSAGE_FAILED("Kafka send message failed. Topic: {}"),
    F_KAFKA_CONSUMER_DIED("Kafka consumer died. Topic: {}"),
    W_KAFKA_LISTENER_INTERRUPTED("Listener thread has been interrupted! Consuming topic name: {}"),
    W_EXECUTOR_SHUT_DOWN("Executor has been shut down. Name or id: {}")
    ;

    private final String template;

    NotableEvents(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
