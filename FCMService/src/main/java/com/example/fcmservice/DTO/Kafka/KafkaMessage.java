package com.example.fcmservice.DTO.Kafka;

import lombok.Builder;

@Builder
public record KafkaMessage<T>(
        String id,
        T body
) {}

