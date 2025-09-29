package com.finpay.common.dto.frauds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckResponse {
    private UUID transactionId;
    private boolean fraudulent;
    private String reason;
}

