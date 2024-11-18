package com.example.notificationservice.API.Notification.Service;

import com.example.notificationservice.CommonDTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {
    private final APINotificationService apiNotificationService;

    @KafkaListener(topics = "notification-common", groupId = "notification-group")
    public void saveCommonNotification(@Payload KafkaMessage<String> message){
        log.info("Save Common Notification");
        apiNotificationService.saveNotification(message.body(), Long.valueOf(message.id()));
    }
}
