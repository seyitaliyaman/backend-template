package com.project.template.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {


    @KafkaListener(topics = "kafka-test-topic")
    public void consume(String data){
        log.info("Data consumed. Data: {}",data);
    }
}
