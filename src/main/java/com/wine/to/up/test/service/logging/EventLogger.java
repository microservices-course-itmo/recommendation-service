package com.wine.to.up.test.service.logging;

import com.wine.to.up.test.service.components.AppMetrics;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;

/**
 * This is specialized version of logback logger.
 * Every time we log specific {@link NotableEvents}
 * we record metric called @{code events_total}
 * with event type as label derived from name of event.
 *
 * <p>
 * Example of instance declaration
 * <pre>{@code
 * class SomeClass {
 *   @InjectEventLogger
 *   KPIEventsLogger logger;
 * }
 * }</pre>
 *
 * <p>
 * Whenever you want to log an event
 * call it as you would typically do with logback
 * {@link EventLogger#trace}
 * {@link EventLogger#debug}
 * {@link EventLogger#info}
 * {@link EventLogger#warn}
 * {@link EventLogger#error}
 */
@AllArgsConstructor
public class EventLogger {
    private final Logger log;
    private final AppMetrics metrics;

    public void trace(NotableEvents event, Object... payload) {
        log.info(event.getTemplate(), payload); // todo sukhoa use Markers and add event name!
        metrics.countEvent(event);
    }

    public void debug(NotableEvents event, Object... payload) {
        log.debug(event.getTemplate(), payload); // todo sukhoa use Markers and add event name!
        metrics.countEvent(event);
    }

    public void info(NotableEvents event, Object... payload) {
        log.info(event.getTemplate(), payload); // todo sukhoa use Markers and add event name!
        metrics.countEvent(event);
    }

    public void warn(NotableEvents event, Object... payload) {
        log.warn(event.getTemplate(), payload); // todo sukhoa use Markers and add event name!
        metrics.countEvent(event);
    }

    public void error(NotableEvents event, Object... payload) {
        log.error(event.getTemplate(), payload); // todo sukhoa use Markers and add event name!
        metrics.countEvent(event);
    }
}
