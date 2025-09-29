package com.finpay.frauds.repositories;

import com.finpay.frauds.models.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FraudCheckRepository extends JpaRepository<FraudCheck, UUID> {
    Optional<FraudCheck> findByTransactionId(UUID transactionId);
}

