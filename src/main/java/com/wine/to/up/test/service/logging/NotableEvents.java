package com.wine.to.up.test.service.logging;

public enum NotableEvents {
    DEFAULT("Default event"),
    EXCEPTION("Some exception occurred"),

    W_KAFKA_SEND_MESSAGE_FAILED("Kafka send message failed. Topic: {}"),
    F_KAFKA_CONSUMER_DIED("Kafka consumer died. Topic: {}"),
    ;

    private final String template;

    NotableEvents(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
