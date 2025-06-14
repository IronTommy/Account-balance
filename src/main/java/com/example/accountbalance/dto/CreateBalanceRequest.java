package com.example.accountbalance.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBalanceRequest {
    @NotBlank(message = "Name is required")
    private String name;
}
