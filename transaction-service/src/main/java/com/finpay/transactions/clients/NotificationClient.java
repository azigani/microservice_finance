package com.finpay.transactions.clients;

import com.finpay.common.dto.notifications.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", url = "http://localhost:8084/notifications")
public interface NotificationClient {

    @PostMapping
    void sendNotification(NotificationRequest request);
}

