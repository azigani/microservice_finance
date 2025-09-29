package com.finpay.notifications.services;

import com.finpay.notifications.models.Notification;
import com.finpay.notifications.models.NotificationRequest;
import com.finpay.notifications.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository repository;
    private final JavaMailSender mailSender;

    public Notification sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .message(request.getMessage())
                .channel(request.getChannel())
                .status("PENDING")
                .build();

        try {
            if ("EMAIL".equalsIgnoreCase(request.getChannel())) {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(request.getUserId()); // assuming userId = email
                mail.setSubject("FinPay Notification");
                mail.setText(request.getMessage());
                mailSender.send(mail);
            } else {
                log.info("Simulating {} notification for {}: {}", request.getChannel(), request.getUserId(), request.getMessage());
            }

            notification.setStatus("SENT");
        } catch (Exception e) {
            log.error("Failed to send notification", e);
            notification.setStatus("FAILED");
        }

        return repository.save(notification);
    }
}

