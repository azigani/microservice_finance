package com.finpay.notifications.controllers;

import com.finpay.notifications.models.Notification;
import com.finpay.notifications.models.NotificationRequest;
import com.finpay.notifications.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public Notification create(@RequestBody NotificationRequest request) {
        return service.sendNotification(request);
    }
}

