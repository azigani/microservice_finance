package com.finpay.frauds.services;

import com.finpay.common.dto.frauds.FraudCheckEvent;
import com.finpay.common.dto.transactions.TransactionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudConsumer {

    private static final Logger log = LoggerFactory.getLogger(FraudConsumer.class);
    private final FraudService fraudService;

    public FraudConsumer(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @KafkaListener(topics = "transactions-topic", groupId = "fraud-service-group")
    public void consume(TransactionCreatedEvent event) {
        log.info("Received transaction from Kafka | id={} | amount={} | user={}",
                event.getId(), event.getAmount(), event.getUserId());

        // Fake fraud check
        boolean fraudulent = event.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0;

        FraudCheckEvent fraudEvent = new FraudCheckEvent(event.getId(), fraudulent);
        // Call fraud check logic
        fraudService.checkFraud(fraudEvent.getTransactionId(), event.getAmount());
    }
}

