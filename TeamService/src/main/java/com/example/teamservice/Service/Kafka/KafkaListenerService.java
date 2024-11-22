package com.example.teamservice.Service.Kafka;

import com.example.teamservice.DTO.Kafka.KafkaMessage;
import com.example.teamservice.DTO.Kafka.Request.UpdateTierPointDTO;
import com.example.teamservice.Entity.Team;
import com.example.teamservice.Repository.TeamRepository;
import com.example.teamservice.Service.TeamService;
import com.example.teamservice.Service.TierService;
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

    private final TeamRepository teamRepository;
    private final TierService tierService;
    private final TeamService teamService;
    private final ObjectMapper objectMapper;
    private final KafkaService kafkaService;

    @KafkaListener(topics = "team-leader-create-rollback", groupId = "team-group")
    public void createLeaderTeamMember(@Payload KafkaMessage<String> message){
        log.info("Rollback");
        teamRepository.deleteById(message.body());
    }

    @KafkaListener(topics = "update-tier-point", groupId = "team-group")
    @Transactional(rollbackFor = Exception.class)
    public void updateTierPoint(@Payload KafkaMessage<UpdateTierPointDTO> message){
        log.info("Update Tier Point");
        try{
            UpdateTierPointDTO updateTierPointDTO = objectMapper.convertValue(message.body(), UpdateTierPointDTO.class);
            Team teamA = teamService.findByTeamNameReturnEntity(updateTierPointDTO.getTeamAName());
            Team teamB = teamService.findByTeamNameReturnEntity(updateTierPointDTO.getTeamBName());
            tierService.updateTierPoint(updateTierPointDTO, teamA, teamB);
        } catch(Exception e){
            kafkaService.updateTierPointRollback(Long.valueOf(message.id()));
        }
    }
}