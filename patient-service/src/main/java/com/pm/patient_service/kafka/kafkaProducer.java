package com.pm.patient_service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class kafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
}
