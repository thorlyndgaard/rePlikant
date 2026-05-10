package com.example.replikant.transactionService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    private Long userId;
    private Double amount;
    private String country;

}