package tr.edu.ku.ulgen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tr.edu.ku.ulgen.dto.UlgenDto;
import tr.edu.ku.ulgen.entity.UlgenData;
import tr.edu.ku.ulgen.repository.UlgenDataRepository;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaListenerService {
    private UlgenDataRepository ulgenDataRepository;

    @KafkaListener(topics = "ulgen", groupId = "groupId")
    void listener(String raw) throws JsonProcessingException {
        log.info("Raw message is received by the listener: {}", raw);

        ObjectMapper mapper = new ObjectMapper();
        UlgenDto ulgenDto = mapper.readValue(raw, UlgenDto.class);

        log.info("Mapped raw message to the UlgenDto.");

        ulgenDataRepository
                .saveOrUpdate(UlgenData.builder()
                        .userId(ulgenDto.getUserId())
                        .activeUsers(ulgenDto.getActiveUser())
                        .latitude(ulgenDto.getLocation().getLatitude())
                        .longitude(ulgenDto.getLocation().getLongitude())
                        .userCity(ulgenDto.getUserCity())
                        .build());
    }
}
