package com.example.teamservice.Service;

import com.example.teamservice.DTO.Kafka.KafkaMessage;
import com.example.teamservice.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    private final TeamRepository teamRepository;

    @KafkaListener(topics = "team-leader-create-rollback", groupId = "team-group")
    public void createLeaderTeamMember(@Payload KafkaMessage<String> message){
        log.info("Rollback");
        teamRepository.deleteById(message.body());
    }
}
