package com.example.replikant.transactionService.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionEvent {

    private Long transactionId;
    private Long userId;
    private Double amount;
    private String country;

}