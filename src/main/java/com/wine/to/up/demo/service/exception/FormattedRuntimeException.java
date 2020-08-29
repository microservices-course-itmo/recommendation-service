package com.wine.to.up.demo.service.exception;

import org.slf4j.helpers.MessageFormatter;

public class FormattedRuntimeException extends RuntimeException {
    public FormattedRuntimeException(String messagePattern, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage());
    }

    public FormattedRuntimeException(String messagePattern, Throwable cause, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage(), cause);
    }
}
