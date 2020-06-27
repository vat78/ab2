package ru.vat78.alfa.ab2.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.vat78.alfa.ab2.model.Payment;
import ru.vat78.alfa.ab2.service.AnalyticsService;

@Service
public class PaymentsKafkaConsumer {

    AnalyticsService analyticsService;

    public PaymentsKafkaConsumer(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @KafkaListener(topics = "RAW_PAYMENTS", groupId = "${spring.kafka.consumer.group-id}")
    public void createEvent(String message) {
        Payment.buildPayment(message).ifPresent(analyticsService::addPayment);
    }
}
