package tr.edu.ku.ulgen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tr.edu.ku.ulgen.dto.UlgenDto;
import tr.edu.ku.ulgen.entity.UlgenData;
import tr.edu.ku.ulgen.repository.UlgenDataRepository;

@Component
@AllArgsConstructor
public class KafkaListenerService {
    private UlgenDataRepository ulgenDataRepository;

    @KafkaListener(topics = "ulgen", groupId = "groupId")
    void listener(String raw) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UlgenDto ulgenDto = mapper.readValue(raw, UlgenDto.class);

        System.out.println("Listener got: " + ulgenDto.toString());

        ulgenDataRepository
                .saveOrUpdate(UlgenData.builder()
                        .userId(ulgenDto.getUserId())
                        .activeUsers(ulgenDto.getActiveUser())
                        .latitude(ulgenDto.getLocation()[0])
                        .longitude(ulgenDto.getLocation()[1])
                        .userCity(ulgenDto.getUserCity())
                .build());
    }
}
