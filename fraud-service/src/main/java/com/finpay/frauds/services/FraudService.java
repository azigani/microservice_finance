package com.finpay.frauds.services;

import com.finpay.common.dto.frauds.FraudCheckResponse;
import com.finpay.frauds.models.FraudCheck;
import com.finpay.frauds.repositories.FraudCheckRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FraudService {

    private final FraudCheckRepository repository;

    public FraudService(FraudCheckRepository repository) {
        this.repository = repository;
    }

    public FraudCheckResponse checkFraud(UUID transactionId, BigDecimal amount) {
        // Example rule: mark as fraud if amount > 10,000
        boolean fraudulent = amount.compareTo(BigDecimal.valueOf(10000)) > 0;
        String reason = fraudulent ? "Amount exceeds fraud threshold" : "Transaction is valid";

        FraudCheck check = new FraudCheck();
        check.setTransactionId(transactionId);
        check.setFraudulent(fraudulent);
        check.setReason(reason);

        repository.save(check);

        return FraudCheckResponse.builder()
                .transactionId(transactionId)
                .fraudulent(fraudulent)
                .reason(reason)
                .build();
    }

    public FraudCheckResponse getFraudStatus(UUID transactionId) {
        FraudCheck fc = repository.findByTransactionId(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("FraudCheck not found"));;

        return FraudCheckResponse.builder()
                .transactionId(fc.getTransactionId())
                .fraudulent(fc.isFraudulent())
                .reason(fc.getReason())
                .build();
    }
}

