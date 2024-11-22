package com.example.teammemberservice.Service;

import com.example.teammemberservice.DTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    @Value("${Team_Leader_Create_Rollback_Topic}")
    private String TeamLeaderCreateRollbackTopic;
    //Kafka
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    void sendTeamLeaderCreateRollbackEvent(Long memberId, String teamName){
        kafkaTemplate.send(TeamLeaderCreateRollbackTopic, new KafkaMessage<>(String.valueOf(memberId), teamName));
    }
}
