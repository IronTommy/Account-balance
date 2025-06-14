package com.example.accountbalance.service;

import com.example.accountbalance.model.Balance;
import com.example.accountbalance.model.Transaction;
import com.example.accountbalance.repository.BalanceRepository;
import com.example.accountbalance.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public Transaction createTransaction(String balanceName, String type, Double amount, String currency) {
        Balance balance = balanceRepository.findByName(balanceName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Balance not found"));

        BigDecimal amountUsd = CurrencyRates.toUsd(BigDecimal.valueOf(amount), currency);
        BigDecimal newBalanceValue = balance.getValue();

        if ("deposit".equalsIgnoreCase(type)) {
            newBalanceValue = newBalanceValue.add(amountUsd);
        } else if ("withdraw".equalsIgnoreCase(type)) {
            newBalanceValue = newBalanceValue.subtract(amountUsd);
            if (newBalanceValue.compareTo(BigDecimal.ZERO) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid transaction type");
        }

        balance.setValue(newBalanceValue);
        balanceRepository.save(balance);

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setBalance(balance);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(String balanceName) {
        Balance balance = balanceRepository.findByName(balanceName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Balance not found"));
        return transactionRepository.findAllByBalance(balance);
    }
}
