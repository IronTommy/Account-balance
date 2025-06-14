package com.example.accountbalance.service;

import com.example.accountbalance.model.Balance;
import com.example.accountbalance.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public Balance createBalance(String name) {
        if (balanceRepository.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance with this name already exists");
        }
        Balance balance = new Balance();
        balance.setName(name);
        balance.setValue(BigDecimal.ZERO);
        return balanceRepository.save(balance);
    }

    public Balance getBalance(String name) {
        return balanceRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Balance not found"));
    }
}
