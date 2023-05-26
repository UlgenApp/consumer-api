package tr.edu.ku.ulgen.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import tr.edu.ku.ulgen.dto.UlgenDto;
import tr.edu.ku.ulgen.util.UlgenDtoSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConsumerConfig class is responsible for creating and configuring Kafka Consumer beans.
 * It reads the required configuration properties from the application properties file.
 * The class is marked with the @Configuration annotation to indicate that it is a Spring configuration class.
 *
 * @author Kaan Turkmen
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    /**
     * Produces a Map containing the Kafka consumer configuration properties.
     *
     * @return a Map containing the configuration properties for the Kafka consumer.
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UlgenDtoSerializer.class);

        return props;
    }

    /**
     * Creates a ConsumerFactory bean for creating Kafka consumers.
     *
     * @return a ConsumerFactory instance configured with the consumer properties.
     */
    @Bean
    public ConsumerFactory<String, UlgenDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Creates a KafkaListenerContainerFactory bean for creating Kafka listener containers.
     *
     * @param consumerFactory the ConsumerFactory instance used to create Kafka consumers.
     * @return a KafkaListenerContainerFactory instance configured with the given consumerFactory.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UlgenDto>> factory(ConsumerFactory<String, UlgenDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, UlgenDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
