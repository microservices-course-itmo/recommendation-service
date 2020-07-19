package com.wine.to.up.test.service.configuration;

import com.wine.to.up.test.service.kafka.BaseKafkaHandler;
import com.wine.to.up.test.service.kafka.KafkaMessageHandler;
import com.wine.to.up.test.service.kafka.TestTopicKafkaMessageHandler;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Configuration
public class KafkaConfiguration {
    /**
     * Library serialization path
     */
    private static final String SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * Library deserialization path
     */
    private static final String DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";

    /**
     * List of kafka servers
     */
    @Value("${spring.kafka.bootstrap-server}")
    private String brokers;

    /**
     * Application consumer group id
     */
    @Value("${spring.kafka.consumer.group-id}")
    private String applicationConsumerGroupId;

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", brokers);
        properties.setProperty("key.serializer", SERIALIZER);
        properties.setProperty("value.serializer", SERIALIZER);

        return new KafkaProducer<>(properties);
    }

    /**
     * Creating general consumer properties. Common for all the consumers
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Properties consumerProperties() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", brokers);
        properties.setProperty("group.id", applicationConsumerGroupId);
        properties.setProperty("auto.offset.reset", "earliest"); // todo sukhoa figure out the meaning
        properties.setProperty("key.deserializer", DESERIALIZER);
        return properties;
    }


    /**
     * Creates consumer based on general properties.
     * <p>
     * Uses custom deserializer as the messages within single topic should be the same type. And
     * the messages in different topics can have different types and require different deserializers
     * <p>
     * Binds the consumer of the topic with the object which is responsible for handling messages from
     * this topic
     * <p>
     * From now on all the messages consumed from given topic will be delegate
     * to {@link KafkaMessageHandler#handle(Object)} of the given handler
     *
     * @param consumerProperties is the general consumer properties. {@link #consumerProperties()}
     * @param handler            which is responsible for handling messages from this topic
     */
    @Bean
    BaseKafkaHandler<String> anotherTopicHandler(Properties consumerProperties,
                                                 TestTopicKafkaMessageHandler handler) {
        // set appropriate deserializer
        consumerProperties.setProperty("value.deserializer", DESERIALIZER);

        // bind consumer with topic name and with appropriate handler
        return new BaseKafkaHandler<>("test", new KafkaConsumer<>(consumerProperties), handler); // todo sukhoa topic property
    }
}
