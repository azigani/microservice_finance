package com.finpay.frauds.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "fraud_checks")
public class FraudCheck {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID transactionId;
    private boolean fraudulent;
    private String reason;

    @CreationTimestamp
    private Instant createdAt;
}

