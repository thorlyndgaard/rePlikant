package com.example.replikant.transactionService.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.replikant.transactionService.event.TransactionEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    private static final String TOPIC = "transaction-topic";

    public void send(TransactionEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}