package com.example.teammemberservice.Service;

import com.example.teammemberservice.DTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListenerService {

    private final APITeamMemberService apiTeamMemberService;

    @KafkaListener(topics = "team-leader-create", groupId = "team-member-group")
    public void createLeaderTeamMember(@Payload KafkaMessage<String> message){
        apiTeamMemberService.createLeaderTeamMember(Long.valueOf(message.id()), message.body());
    }

    @KafkaListener(topics = "team-invitee", groupId = "team-member-group")
    public void teamInvite(@Payload KafkaMessage<String> message){
        apiTeamMemberService.createTeamMember(Long.valueOf(message.id()), message.body());
    }
}
