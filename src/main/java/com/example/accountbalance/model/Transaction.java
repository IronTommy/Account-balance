package com.example.accountbalance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Transaction {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "balance_id", nullable = false)
    private Balance balance;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
