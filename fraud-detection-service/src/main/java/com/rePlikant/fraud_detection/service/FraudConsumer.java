package com.rePlikant.fraud_detection.service;

import com.rePlikant.fraud_detection.event.TransactionEvent;
import com.rePlikant.fraud_detection.entity.FraudAnalysis;
import com.rePlikant.fraud_detection.repository.FraudAnalysisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class FraudConsumer {

    private final FraudAnalysisRepository repository;

    @KafkaListener(topics = "transaction-topic", groupId = "fraud-group")
    public void consume(TransactionEvent event){
        log.info("Receiving transaction {} for analysis...", event.getTransactionId());

        String status = event.getAmount().doubleValue() >= 10000 ? "REJECTED" : "APPROVED";

        if ("REJECTED".equals(status)) {
            log.error("🚨 FRAUD DETECTED! Transaction ID: {} | Amount: {}", event.getTransactionId(), event.getAmount());
        }

        FraudAnalysis analysis = FraudAnalysis.builder()
                .transactionId(event.getTransactionId())
                .amount(event.getAmount())
                .status(status)
                .analyzedAt(LocalDateTime.now())
                .build();

        repository.save(analysis);
        log.info("Analysis saved with status: {}", status);
    }
}