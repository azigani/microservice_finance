package com.finpay.authservice.repositories;

import com.finpay.authservice.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
