package com.wine.to.up.recommendation.service.messaging.serialization;

import com.wine.to.up.recommendation.service.api.message.KafkaMessageSentEventOuterClass.KafkaMessageSentEvent;
import org.apache.kafka.common.serialization.Serializer;

/**
 * Serializer for {@link KafkaMessageSentEvent}
 */
public class EventSerializer implements Serializer<KafkaMessageSentEvent> {
    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] serialize(String topic, KafkaMessageSentEvent data) {
        return data.toByteArray();
    }
}
