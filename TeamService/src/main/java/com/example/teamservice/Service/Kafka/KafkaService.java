package com.example.teamservice.Service.Kafka;

import com.example.teamservice.DTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {
    //Kafka
    private static final String TOPIC = "update-tier-point-rollback";
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    public void updateTierPointRollback(Long matchId){
        kafkaTemplate.send(TOPIC, new KafkaMessage<>("UpdateTierPointRollback", matchId));
    }
}
