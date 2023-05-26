package tr.edu.ku.ulgen.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import tr.edu.ku.ulgen.dto.UlgenDto;

import java.util.Map;

/**
 * UlgenDtoSerializer class is a custom Kafka serializer implementation for serializing
 * UlgenDto objects into byte arrays. This class implements the Serializer interface
 * and uses the Jackson ObjectMapper for serialization.
 *
 * @author Kaan Turkmen
 */
public class UlgenDtoSerializer implements Serializer<UlgenDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Configures the serializer. This implementation is a no-op.
     *
     * @param configs the configuration settings.
     * @param isKey   whether the serializer is used for serializing keys or values.
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    /**
     * Serializes the given UlgenDto object into a byte array.
     *
     * @param topic    the topic associated with the data.
     * @param ulgenDto the UlgenDto object to serialize.
     * @return a byte array representation of the UlgenDto object.
     * @throws SerializationException if an error occurs during serialization.
     */
    @Override
    public byte[] serialize(String topic, UlgenDto ulgenDto) {
        try {
            if (ulgenDto == null) {
                System.out.println("Null received at serializing.");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(ulgenDto);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing UlgenDto to byte[]");
        }
    }

    /**
     * Closes the serializer. This implementation is a no-op.
     */
    @Override
    public void close() {
    }
}

