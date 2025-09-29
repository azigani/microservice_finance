package com.finpay.notifications.services;

import com.finpay.common.dto.frauds.FraudCheckEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FraudNotificationConsumer {

    @KafkaListener(topics = "fraud-check", groupId = "notification-service-group")
    public void consume(FraudCheckEvent event) {
        if (event.isFraudulent()) {
            System.out.println("🚨 Send fraud alert for tx: " + event.getTransactionId());
        }
    }
}

