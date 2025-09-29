package com.finpay.accounts.services;

import com.finpay.accounts.models.Account;
import com.finpay.accounts.repositories.AccountRepository;
import com.finpay.common.dto.accounts.AccountDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AccountDto createAccount(String ownerEmail, BigDecimal initialBalance) {
        Account acc = new Account();
        acc.setOwnerEmail(ownerEmail);
        acc.setBalance(initialBalance != null ? initialBalance : BigDecimal.ZERO);

        Account saved = repository.save(acc);

        return new AccountDto(saved.getId(), saved.getOwnerEmail(), saved.getBalance());
    }

    @Transactional
    public AccountDto debit(UUID accountId, BigDecimal amount) {
        Account acc = repository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (acc.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance().subtract(amount));
        repository.save(acc);

        return new AccountDto(acc.getId(), acc.getOwnerEmail(), acc.getBalance());
    }

    @Transactional
    public AccountDto credit(UUID accountId, BigDecimal amount) {
        Account acc = repository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        acc.setBalance(acc.getBalance().add(amount));
        repository.save(acc);

        return new AccountDto(acc.getId(), acc.getOwnerEmail(), acc.getBalance());
    }

    public AccountDto getAccount(UUID accountId) {
        Account acc = repository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return new AccountDto(acc.getId(), acc.getOwnerEmail(), acc.getBalance());
    }
}

