package com.example.accountbalance.repository;

import com.example.accountbalance.model.Balance;
import com.example.accountbalance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, java.util.UUID> {
    List<Transaction> findAllByBalance(Balance balance);
}
