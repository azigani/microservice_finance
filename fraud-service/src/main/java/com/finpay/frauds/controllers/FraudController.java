package com.finpay.frauds.controllers;

import com.finpay.common.dto.frauds.FraudCheckRequest;
import com.finpay.common.dto.frauds.FraudCheckResponse;
import com.finpay.frauds.services.FraudService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/frauds")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @PostMapping("/check")
    public FraudCheckResponse checkFraud(@RequestBody FraudCheckRequest request) {
        return fraudService.checkFraud(request.getTransactionId(), request.getAmount());
    }

    @GetMapping("/transactions/{transactionId}")
    public FraudCheckResponse getFraudStatus(@PathVariable("transactionId") UUID transactionId) {
        return fraudService.getFraudStatus(transactionId);
    }
}


