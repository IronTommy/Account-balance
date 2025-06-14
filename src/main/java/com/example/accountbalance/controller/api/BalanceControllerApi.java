package com.example.accountbalance.controller.api;

import com.example.accountbalance.dto.CreateBalanceRequest;
import com.example.accountbalance.dto.CreateTransactionRequest;
import com.example.accountbalance.model.Balance;
import com.example.accountbalance.model.Transaction;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/balances")
public interface BalanceControllerApi {
    @PostMapping
    ResponseEntity<Balance> createBalance(@Valid @RequestBody CreateBalanceRequest request);

    @GetMapping("/{name}")
    ResponseEntity<Balance> getBalance(@PathVariable String name);

    @PostMapping("/{name}/transactions")
    ResponseEntity<Transaction> createTransaction(
            @Valid
            @PathVariable String name,
            @RequestBody CreateTransactionRequest request);

    @GetMapping("/{name}/transactions")
    ResponseEntity<List<Transaction>> getTransactions(@PathVariable String name);
}
