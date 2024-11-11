package com.example.matchservice.DTO.Kafka;

import lombok.Builder;

@Builder
public record KafkaMessage<T>(
        String id,
        T body
) {}
