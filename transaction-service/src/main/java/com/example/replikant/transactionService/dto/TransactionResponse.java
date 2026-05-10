package com.example.replikant.transactionService.dto;

import java.time.LocalDateTime;

import com.example.replikant.transactionService.model.TransactionStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {

    private Long id;
    private Long userId;
    private Double amount;
    private String country;
    private TransactionStatus status;
    private LocalDateTime createdAt;

}
