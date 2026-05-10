package com.example.replikant.transactionService.mapper;

import com.example.replikant.transactionService.dto.TransactionRequest;
import com.example.replikant.transactionService.dto.TransactionResponse;
import com.example.replikant.transactionService.event.TransactionEvent;
import com.example.replikant.transactionService.model.Transaction;

public class TransactionMapper {

    public static Transaction toEntity(TransactionRequest request) {
        if (request == null)
            return null;

        return Transaction.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .country(request.getCountry())
                .build();
    }

    public static TransactionResponse toResponse(Transaction transaction) {
        if (transaction == null)
            return null;

        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setUserId(transaction.getUserId());
        response.setAmount(transaction.getAmount());
        response.setCountry(transaction.getCountry());
        response.setStatus(transaction.getStatus());
        response.setCreatedAt(transaction.getCreatedAt());

        return response;
    }

    public static TransactionEvent toEvent(Transaction transaction) {
        if (transaction == null)
            return null;

        TransactionEvent event = new TransactionEvent();
        event.setTransactionId(transaction.getId());
        event.setUserId(transaction.getUserId());
        event.setAmount(transaction.getAmount());
        event.setCountry(transaction.getCountry());

        return event;
    }
}