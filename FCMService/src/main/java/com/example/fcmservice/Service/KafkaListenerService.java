package com.example.fcmservice.Service;

import com.example.fcmservice.DTO.Kafka.KafkaMessage;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {
    private final FCMService fcmService;

    @KafkaListener(topics = "send-fcm-message", groupId = "fcm-group")
    public void saveCommonNotification(@Payload KafkaMessage<String> message) throws FirebaseMessagingException {
        log.info("Send FCM Message");
        fcmService.sendMessageForOneMember(Long.valueOf(message.id()), message.body());
    }
}

