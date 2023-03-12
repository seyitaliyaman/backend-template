package com.project.template.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produceData(String data){
        try {
            kafkaTemplate.send("kafka-test-topic",data);
        }catch (Exception e){
            log.error("An error occurred while sending data. {}",data, e);
        }
    }
}
