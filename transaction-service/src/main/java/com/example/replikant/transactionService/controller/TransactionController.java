package com.example.replikant.transactionService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.replikant.transactionService.dto.TransactionRequest;
import com.example.replikant.transactionService.dto.TransactionResponse;
import com.example.replikant.transactionService.event.TransactionEvent;
import com.example.replikant.transactionService.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody TransactionRequest request) {

        TransactionResponse response = service.create(request);

        TransactionEvent event = new TransactionEvent();
        event.setTransactionId(response.getId());
        event.setUserId(response.getUserId());
        event.setAmount(response.getAmount());
        event.setCountry(response.getCountry());


        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(
            @PathVariable Long id,
            @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}