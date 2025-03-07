package org.sds.sonizone.payment.service;


import org.sds.sonizone.payment.dto.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @KafkaListener(topics = "order-topic", groupId = "payment-group")
    public void processPayment(OrderRequest orderRequest) {
        logger.info("Processing payment for Order ID: " + orderRequest);
        System.out.println("Processing payment for Order ID: " + orderRequest);
    }
}

