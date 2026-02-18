package com.lc.analyticsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.analyticsservice.model.PatientEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(PatientEvent event) {
        log.info("Received event: {}", event);
        try {
            String patientEvent = new ObjectMapper().writeValueAsString(event);
            log.info("Received Patient Event {}", patientEvent);
        } catch (IOException e) {
            log.error("Error while reading event {}", e.getMessage());
        }
    }
}
