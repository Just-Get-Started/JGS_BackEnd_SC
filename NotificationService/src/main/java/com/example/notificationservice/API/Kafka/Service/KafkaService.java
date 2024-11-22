package com.example.notificationservice.API.Kafka.Service;

import com.example.notificationservice.CommonDTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {
    private static final String FCM_TOPIC = "send-fcm-message";
    private static final String INVITE_TOPIC = "team-invite";
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    public void sendFCMMessage(Long memberId, String message){
        kafkaTemplate.send(FCM_TOPIC, new KafkaMessage<>(String.valueOf(memberId), message));
    }

    public void teamInvite(Long memberId, String teamName){
        kafkaTemplate.send(INVITE_TOPIC, new KafkaMessage<>(String.valueOf(memberId), teamName));
    }
}