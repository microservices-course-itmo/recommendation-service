package com.wine.to.up.test.service.exception;

public class KafkaRuntimeException extends FormattedRuntimeException {
    public KafkaRuntimeException(String messagePattern, Object... params) {
        super(messagePattern, params);
    }

    public KafkaRuntimeException(String messagePattern, Throwable cause, Object... params) {
        super(messagePattern, cause, params);
    }
}
