package com.wine.to.up.notification.service.logging;

import com.wine.to.up.commonlib.logging.NotableEvent;

public enum NotificationServiceNotableEvents implements NotableEvent {
    NOTIFICATION_EVENT("Something happened"),
    ANOTHER_NOTIFICATION_EVENT("Something else happened");

    private final String template;

    NotificationServiceNotableEvents(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    @Override
    public String getName() {
        return name();
    }


}
