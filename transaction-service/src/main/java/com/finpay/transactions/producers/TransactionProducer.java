package com.finpay.transactions.producers;

import com.finpay.common.dto.transactions.TransactionCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class TransactionProducer {

    private final KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate;

    public TransactionProducer(KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(TransactionCreatedEvent event) {
        kafkaTemplate.send("transactions-topic", event.getId().toString(), event);
    }
}

