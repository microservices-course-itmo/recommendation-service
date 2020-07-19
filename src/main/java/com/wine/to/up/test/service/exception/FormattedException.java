package com.wine.to.up.test.service.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * Formatted exception class
 */
public class FormattedException extends Exception {
    public FormattedException(String messagePattern, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage());
    }

    public FormattedException(String messagePattern, Throwable cause, Object... params) {
        super(MessageFormatter.arrayFormat(messagePattern, params).getMessage(), cause);
    }
}
