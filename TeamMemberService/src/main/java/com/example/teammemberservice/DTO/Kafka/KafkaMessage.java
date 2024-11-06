package com.example.teammemberservice.DTO.Kafka;

import lombok.Builder;

@Builder
public record KafkaMessage<T>(
        String id,
        T body
) {}

