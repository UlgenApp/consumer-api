package tr.edu.ku.ulgen.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import tr.edu.ku.ulgen.dto.UlgenDto;

import java.util.Map;

public class UlgenDtoSerializer implements Serializer<UlgenDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, UlgenDto ulgenDto) {
        try {
            if (ulgenDto == null){
                System.out.println("Null received at serializing.");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(ulgenDto);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing UlgenDto to byte[]");
        }
    }

    @Override
    public void close() {
    }
}

