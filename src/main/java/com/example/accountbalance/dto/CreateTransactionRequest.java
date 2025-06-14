package com.example.accountbalance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateTransactionRequest {
    @NotBlank(message = "Type is required")
    private String type; // deposit or withdraw

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Currency is required")
    private String currency; // USD, EUR, BYN, RUB
}
