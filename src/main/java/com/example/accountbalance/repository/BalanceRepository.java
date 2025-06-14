package com.example.accountbalance.repository;

import com.example.accountbalance.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    boolean existsByName(String name);
    Optional<Balance> findByName(String name);
}
