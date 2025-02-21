package org.sds.sonizone.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<String> getOrderDetails(){
        logger.info("Successfully retrieved order details!");
        return ResponseEntity.ok("Order details fetched successfully!");
    }

    @GetMapping("/callPaymentService")
    public ResponseEntity<String> callPaymentService(){
        logger.info("starts: Inside callPaymentService() method.");
        //ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8001/payment/returnPaymentData", String.class);
        //Added changes for run in container, use container name: payment-service
        ResponseEntity<String> response = restTemplate.getForEntity("http://payment-service:8001/payment/returnPaymentData", String.class);
        logger.info("ends: Inside callPaymentService() method and response : {}" + response);
        return response;
    }
}
