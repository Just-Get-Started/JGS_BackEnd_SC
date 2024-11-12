package com.example.matchservice.Service.Kafka;

import com.example.matchservice.DTO.Kafka.KafkaMessage;
import com.example.matchservice.Service.APIMatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {

    private final APIMatchService apiMatchService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "update-tier-point-rollback", groupId = "match-group")
    @Transactional(rollbackFor = Exception.class)
    public void updateTierPoint(@Payload KafkaMessage<Long> message){
        log.info("Update Tier Point Rollback");
        Long matchId = objectMapper.convertValue(message.body(), Long.class);
        apiMatchService.rollbackMatchScore(matchId);
    }
}
