package com.finpay.common.dto.frauds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckEvent {
    private UUID transactionId;
    private boolean fraudulent;
}

