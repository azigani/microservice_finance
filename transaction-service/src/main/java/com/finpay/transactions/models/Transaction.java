package com.finpay.transactions.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID fromAccountId;
    private UUID toAccountId;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(unique = true)
    private String idempotencyKey;

    @CreationTimestamp
    private Instant createdAt;

    public enum Status { PENDING, COMPLETED, FAILED }
}

