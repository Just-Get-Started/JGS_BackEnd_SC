package com.example.notificationservice.API.Notification.Service;

import com.example.notificationservice.CommonDTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {
    //Kafka
    private static final String FCM_TOPIC = "send-fcm-message";
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    public void sendFCMMessage(Long memberId, String message){
        kafkaTemplate.send(FCM_TOPIC, new KafkaMessage<>(String.valueOf(memberId), message));
    }
}