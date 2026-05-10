package com.rePlikant.fraud_detection.event;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionEvent {

    private Long transactionId;
    private Long userId;
    private BigDecimal amount;
    private String country;
}