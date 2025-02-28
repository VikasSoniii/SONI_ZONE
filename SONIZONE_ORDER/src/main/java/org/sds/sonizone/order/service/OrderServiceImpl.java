package org.sds.sonizone.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final String TOPIC = "order-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public ResponseEntity<String> placeOrder(String orderId) {
        logger.info("Order placed successfully for order is: {}", orderId);
        kafkaTemplate.send(TOPIC, orderId);
        return ResponseEntity.ok("Order Placed Successfully!");
    }
}
