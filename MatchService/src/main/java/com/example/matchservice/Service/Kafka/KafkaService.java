package com.example.matchservice.Service.Kafka;

import com.example.matchservice.DTO.Kafka.KafkaMessage;
import com.example.matchservice.DTO.OpenFeignDTO.UpdateTierPointDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {
    //Kafka
    private static final String TOPIC = "update-tier-point";
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    public void updateTierPoint(Long matchId, UpdateTierPointDTO updateTierPointDTO){
        kafkaTemplate.send(TOPIC, new KafkaMessage<>(String.valueOf(matchId), updateTierPointDTO));
    }
}