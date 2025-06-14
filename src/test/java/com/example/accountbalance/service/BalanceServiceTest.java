package com.example.accountbalance.service;

import com.example.accountbalance.model.Balance;
import com.example.accountbalance.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BalanceServiceTest {

    private final BalanceRepository balanceRepository = mock(BalanceRepository.class);
    private final BalanceService balanceService = new BalanceService(balanceRepository);

    @Test
    void testCreateBalanceSuccess() {
        when(balanceRepository.existsByName("test")).thenReturn(false);
        when(balanceRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Balance balance = balanceService.createBalance("test");
        assertEquals("test", balance.getName());
        assertEquals(0.0, balance.getValue().doubleValue());
    }

    @Test
    void testCreateBalanceDuplicate() {
        when(balanceRepository.existsByName("test")).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> {
            balanceService.createBalance("test");
        });
    }

    @Test
    void testGetBalanceFound() {
        Balance b = new Balance();
        b.setName("my");
        b.setValue(java.math.BigDecimal.ZERO);
        when(balanceRepository.findByName("my")).thenReturn(Optional.of(b));

        Balance found = balanceService.getBalance("my");
        assertEquals("my", found.getName());
    }

    @Test
    void testGetBalanceNotFound() {
        when(balanceRepository.findByName("notfound")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            balanceService.getBalance("notfound");
        });
    }
}
