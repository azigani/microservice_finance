package com.finpay.accounts.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;
}
