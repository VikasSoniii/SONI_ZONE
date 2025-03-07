package org.sds.sonizone.order.controller;


import org.sds.sonizone.order.dto.OrderRequest;
import org.sds.sonizone.order.security.JwtUtil;
import org.sds.sonizone.order.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/token")
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }

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

   /* @PostMapping("/place/{orderId}")
    public ResponseEntity<String> placeOrder(@PathVariable String orderId) {
        logger.info("---------Inside OrderController class--------");
        return orderService.placeOrder(orderId);
    }*/


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("starts: Inside createOrder and request data is: " + orderRequest);
        return orderService.createOrder(orderRequest);
    }
}
