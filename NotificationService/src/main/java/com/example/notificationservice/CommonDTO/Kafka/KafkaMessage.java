package com.example.notificationservice.CommonDTO.Kafka;

import lombok.Builder;

@Builder
public record KafkaMessage<T>(
        String id,
        T body
) {}