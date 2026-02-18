package com.lc.patient_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.patient_service.model.Patient;
import com.lc.patient_service.model.PatientEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, PatientEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, PatientEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        PatientEvent patientEvent = new PatientEvent(patient.getId().toString(),
                patient.getName(), patient.getEmail(), "PATIENT_CREATED");
//        ObjectMapper mapper =  new ObjectMapper();
        try {
//            byte[] event = mapper.writeValueAsBytes(patientEvent);
            kafkaTemplate.send("patient", patientEvent);
        } catch (Exception e) {
            log.error("Error while sending patient event: {}", patientEvent, e);
        }
    }
}
