package com.finpay.accounts.controllers;

import com.finpay.accounts.services.AccountService;
import com.finpay.common.dto.accounts.AccountDto;
import com.finpay.common.dto.accounts.CreateAccountRequest;
import com.finpay.common.dto.accounts.CreditRequest;
import com.finpay.common.dto.accounts.DebitRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
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

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody CreateAccountRequest request) {
        AccountDto dto = service.createAccount(request.getOwnerEmail(), request.getInitialBalance());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/debit")
    public ResponseEntity<AccountDto> debit(@RequestBody DebitRequest request) {
        return ResponseEntity.ok(service.debit(request.getAccountId(), request.getAmount()));
    }

    @PostMapping("/credit")
    public ResponseEntity<AccountDto> credit(@RequestBody CreditRequest request) {
        return ResponseEntity.ok(service.credit(request.getAccountId(), request.getAmount()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getAccount(id));
    }
}
