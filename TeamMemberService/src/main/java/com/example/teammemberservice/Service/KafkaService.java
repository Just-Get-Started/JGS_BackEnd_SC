package com.example.teammemberservice.Service;

import com.example.teammemberservice.DTO.Kafka.KafkaMessage;
import com.example.teammemberservice.Entity.TeamMember;
import com.example.teammemberservice.Entity.TeamMemberRole;
import com.example.teammemberservice.Exception.BusinessLogicException;
import com.example.teammemberservice.Exception.TeamMemberExceptionType;
import com.example.teammemberservice.Repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    @Value("${Team_Leader_Create_Rollback_Topic}")
    private String TeamLeaderCreateRollbackTopic;

    private final TeamMemberRepository teamMemberRepository;
    //Kafka
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    @KafkaListener(topics = "team-leader-create", groupId = "team-member-group")
    public void createLeaderTeamMember(@Payload KafkaMessage<String> message){
        TeamMember teamMember = TeamMember.builder()
                .memberId(Long.valueOf(message.id()))
                .teamName(message.body())
                .role(TeamMemberRole.Leader)
                .build();
        try{
            teamMemberRepository.save(teamMember);
        } catch(Exception e){
            log.warn("팀 리더 생성 실패로 인한 보상 트랜잭션 실행");
            sendTeamLeaderCreateRollbackEvent(message.id(), message.body());
        }
    }

    private void sendTeamLeaderCreateRollbackEvent(String memberId, String teamName){
        kafkaTemplate.send(TeamLeaderCreateRollbackTopic, new KafkaMessage<>(String.valueOf(memberId), teamName));
    }
}
