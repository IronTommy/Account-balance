package com.example.accountbalance.service;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyRates {
    // Пример курса валют
    public static final Map<String, BigDecimal> RATES = Map.of(
            "USD", BigDecimal.valueOf(1.00),
            "EUR", BigDecimal.valueOf(1.07),
            "BYN", BigDecimal.valueOf(0.31),
            "RUB", BigDecimal.valueOf(0.011)
    );

    public static BigDecimal toUsd(BigDecimal amount, String currency) {
        if (!RATES.containsKey(currency)) {
            throw new IllegalArgumentException("Unknown currency: " + currency);
        }
        return amount.multiply(RATES.get(currency));
    }
}
