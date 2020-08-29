package com.wine.to.up.demo.service.configuration;

import com.wine.to.up.api.message.KafkaServiceEventOuterClass.KafkaServiceEvent;
import org.apache.kafka.common.serialization.Serializer;

/**
 * Serializer for {@link KafkaServiceEvent}
 */
public class EventSerializer implements Serializer<KafkaServiceEvent> {
    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] serialize(String topic, KafkaServiceEvent data) {
        return data.toByteArray();
    }
}
