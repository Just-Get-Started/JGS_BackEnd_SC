package com.example.sseservice.Service;

import com.example.sseservice.DTO.Kafka.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {
    private final SSEService sseService;

    @KafkaListener(topics = "send-sse", groupId = "sse-group")
    public void sendSSEMessage(@Payload KafkaMessage<String> message){
        log.info("Send SSE Message");
        sseService.newChatRoom(Long.valueOf(message.id()), Long.valueOf(message.body()));
    }
}
