package com.finpay.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback/transactions")
    public ResponseEntity<String> transactionFallback() {
        return ResponseEntity.ok("Transaction Service is currently unavailable. Please try again later.");
    }
}
