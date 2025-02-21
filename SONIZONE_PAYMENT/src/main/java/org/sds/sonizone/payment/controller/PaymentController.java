package org.sds.sonizone.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @GetMapping
    public ResponseEntity<String> getPaymentDetails(){
        logger.info("Successfully retrieved payment details!");
        return ResponseEntity.ok("Payment details fetched successfully!");
    }

    @GetMapping("/returnPaymentData")
    public ResponseEntity<String> paymentData(){
        logger.info("Successfully retrieved response from Payment Service.!");
        return ResponseEntity.ok("Response retrieved from Payment Service.");
    }
}
