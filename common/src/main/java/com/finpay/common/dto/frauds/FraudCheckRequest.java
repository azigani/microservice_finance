package com.finpay.common.dto.frauds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckRequest {
    private UUID transactionId;
    private BigDecimal amount;
}

