package com.example.teamservice.Config.Kafka;

import com.example.teamservice.DTO.Kafka.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${Kafka_Url}")
    private String url;

    @Value("${Kafka_Message_Team_Service_Package}")
    private String kafkaMessagePackage;

    @Bean
    public ConsumerFactory<String, KafkaMessage<?>> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, url);  // Kafka 서버 주소
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "team-group");

        // Key Deserializer는 StringDeserializer로 설정
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Value Deserializer는 ErrorHandlingDeserializer로 설정
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        // JsonDeserializer 설정
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");  // 모든 패키지 허용
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, KafkaMessage.class.getName());  // 역직렬화할 타입 지정
        // TYPE_MAPPINGS를 사용하여 패키지 간 클래스 매핑
        configProps.put(JsonSerializer.TYPE_MAPPINGS, kafkaMessagePackage);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage<?>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage<?>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
