package com.finpay.transactions.clients;

import com.finpay.common.dto.accounts.AccountDto;
import com.finpay.common.dto.accounts.CreditRequest;
import com.finpay.common.dto.accounts.DebitRequest;
import com.finpay.transactions.configs.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(
        name = "account-service",
        url = "http://localhost:8082/accounts",
        configuration = FeignConfig.class
)
public interface AccountClient {

    @PostMapping("/debit")
    AccountDto debit(DebitRequest request);

    @PostMapping("/credit")
    AccountDto credit(CreditRequest request);

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") UUID id);
}

