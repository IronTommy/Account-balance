package com.example.accountbalance.controller.impl;

import com.example.accountbalance.controller.api.BalanceControllerApi;
import com.example.accountbalance.dto.CreateBalanceRequest;
import com.example.accountbalance.dto.CreateTransactionRequest;
import com.example.accountbalance.model.Balance;
import com.example.accountbalance.model.Transaction;
import com.example.accountbalance.service.BalanceService;
import com.example.accountbalance.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceControllerImpl implements BalanceControllerApi {

    private final BalanceService balanceService;
    private final TransactionService transactionService;

    @Override
    public ResponseEntity<Balance> createBalance(CreateBalanceRequest request) {
        Balance balance = balanceService.createBalance(request.getName());
        return ResponseEntity.ok(balance);
    }

    @Override
    public ResponseEntity<Balance> getBalance(String name) {
        Balance balance = balanceService.getBalance(name);
        return ResponseEntity.ok(balance);
    }

    @Override
    public ResponseEntity<Transaction> createTransaction(String name, CreateTransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(
                name,
                request.getType(),
                request.getAmount(),
                request.getCurrency()
        );
        return ResponseEntity.ok(transaction);
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactions(String name) {
        List<Transaction> transactions = transactionService.getTransactions(name);
        return ResponseEntity.ok(transactions);
    }
}
