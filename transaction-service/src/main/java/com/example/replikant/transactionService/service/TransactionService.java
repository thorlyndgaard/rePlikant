package com.example.replikant.transactionService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.replikant.transactionService.dto.TransactionRequest;
import com.example.replikant.transactionService.dto.TransactionResponse;
import com.example.replikant.transactionService.event.TransactionEvent;
import com.example.replikant.transactionService.kafka.TransactionProducer;
import com.example.replikant.transactionService.mapper.TransactionMapper;
import com.example.replikant.transactionService.model.Transaction;
import com.example.replikant.transactionService.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionProducer producer;

    public TransactionResponse create(TransactionRequest request) {

        Transaction transaction = TransactionMapper.toEntity(request);

        Transaction saved = repository.save(transaction);

        TransactionEvent event = TransactionMapper.toEvent(saved);
        producer.send(event);

        return TransactionMapper.toResponse(saved);
    }

    public List<TransactionResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TransactionResponse findById(Long id) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        return TransactionMapper.toResponse(transaction);
    }

    public TransactionResponse update(Long id, TransactionRequest request) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setUserId(request.getUserId());
        transaction.setAmount(request.getAmount());
        transaction.setCountry(request.getCountry());

        Transaction updated = repository.save(transaction);

        return TransactionMapper.toResponse(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}