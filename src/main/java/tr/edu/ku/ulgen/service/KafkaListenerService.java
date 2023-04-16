package tr.edu.ku.ulgen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tr.edu.ku.ulgen.dto.UlgenDto;
import tr.edu.ku.ulgen.entity.UlgenData;
import tr.edu.ku.ulgen.repository.UlgenDataRepository;

/**
 * KafkaListenerService is a class responsible for listening to
 * the Kafka topic 'ulgen' and processing the received messages.
 * It deserializes the received messages into UlgenDto instances,
 * and then saves or updates the corresponding UlgenData entities
 * in the database using the UlgenDataRepository.
 *
 * @author Kaan Turkmen
 */
@Component
@AllArgsConstructor
@Slf4j
public class KafkaListenerService {
    private UlgenDataRepository ulgenDataRepository;

    /**
     * Method that listens for messages on the 'ulgen' topic and processes them.
     *
     * @param raw the raw message received from the Kafka topic.
     * @throws JsonProcessingException if there is an error when deserializing the raw message.
     */
    @KafkaListener(topics = "ulgen", groupId = "groupId")
    void listener(String raw) throws JsonProcessingException {
        log.info("Raw message is received by the listener: {}", raw);

        ObjectMapper mapper = new ObjectMapper();
        UlgenDto ulgenDto = mapper.readValue(raw, UlgenDto.class);

        log.info("Mapped raw message to the UlgenDto.");

        try {
            ulgenDataRepository
                    .saveOrUpdate(UlgenData.builder()
                            .userId(ulgenDto.getUserId())
                            .activeUsers(ulgenDto.getActiveUser())
                            .latitude(ulgenDto.getLocation().getLatitude())
                            .longitude(ulgenDto.getLocation().getLongitude())
                            .userCity(ulgenDto.getUserCity())
                            .build());
        } catch (PersistenceException e) {
            log.error("Could not run saveOrUpdate on the database.");
            log.error("Database is not reachable, {}", e.getMessage());
        }
    }
}
