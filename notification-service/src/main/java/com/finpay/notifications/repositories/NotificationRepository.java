package com.finpay.notifications.repositories;

import com.finpay.notifications.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

