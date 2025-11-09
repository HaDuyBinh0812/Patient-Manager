package com.pm.patient_service.kafka;

import com.pm.patient_service.model.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendEvent(Patient patient) {
        PatientEvent patientEvent = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try{
            kafkaTemplate.send("patient", patientEvent.toByteArray())
                    .whenComplete((result, ex) -> {
                        if (ex == null) {
                            log.info("✅ Message sent successfully to topic '{}', partition={}, offset={}",
                                    result.getRecordMetadata().topic(),
                                    result.getRecordMetadata().partition(),
                                    result.getRecordMetadata().offset());
                        } else {
                            log.error("❌ Failed to send message", ex);
                        }
                    });

        }catch (Exception e){
            log.error("Error sending PatientCreated event: {}",  patientEvent);
        }
    }
}
