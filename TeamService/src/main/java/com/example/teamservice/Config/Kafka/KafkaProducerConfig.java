package com.example.teamservice.Config.Kafka;

import com.example.teamservice.DTO.Kafka.KafkaMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${Kafka_Url}")
    private String url;

    @Value("${Kafka_Message_Team_Service_Package}")
    private String kafkaMessagePackage;

    @Bean
    public ProducerFactory<String, KafkaMessage<?>> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, url); // Kafka 컨테이너 이름 사용
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // TYPE_MAPPINGS를 사용하여 패키지 간 클래스 매핑
        configProps.put(JsonSerializer.TYPE_MAPPINGS, kafkaMessagePackage);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
