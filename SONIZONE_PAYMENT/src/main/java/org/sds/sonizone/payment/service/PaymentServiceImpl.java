package org.sds.sonizone.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @KafkaListener(topics = "order-topic", groupId = "payment-group")
    public void processPayment(String orderId) {
        logger.info("Processing payment for Order ID: " + orderId);
        System.out.println("Processing payment for Order ID: " + orderId);
    }
}

