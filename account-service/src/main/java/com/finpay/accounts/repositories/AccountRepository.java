package com.finpay.accounts.repositories;

import com.finpay.accounts.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByOwnerEmail(String email);
}

