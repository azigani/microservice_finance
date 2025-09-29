package com.finpay.transactions.controllers;

import com.finpay.common.dto.transactions.TransactionResponse;
import com.finpay.common.dto.transactions.TransferRequest;
import com.finpay.transactions.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // Extract JWT claims with @AuthenticationPrincipal
    @GetMapping("/me")
    public Map<String, Object> myAccount(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "user_id", jwt.getClaim("user_id"),
                "email", jwt.getClaim("email"),
                "roles", jwt.getClaim("scope")
        );
    }

    @Operation(summary = "Transfer money between accounts", description = "Requires valid JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Transaction accepted"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(
            @RequestHeader("Idempotency-Key") String key,
            @RequestBody TransferRequest request
    ) {
        TransactionResponse tx = service.transfer(key, request);
        return ResponseEntity.accepted().body(tx);
    }

    @GetMapping("/{id}")
    public TransactionResponse status(@PathVariable UUID id) {
        return service.getStatus(id);
    }
}
