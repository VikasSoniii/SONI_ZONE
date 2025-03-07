package org.sds.sonizone.order.service;

import org.sds.sonizone.order.dto.OrderRequest;
import org.sds.sonizone.order.entity.Order;
import org.sds.sonizone.order.producer.KafkaProducer;
import org.sds.sonizone.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final String TOPIC = "order-topic";
    @Autowired
    KafkaProducer<OrderRequest> kafkaProducer;

    @Autowired
    OrderRepository orderRepository;

    //@Autowired
    //private KafkaTemplate<String, OrderRequest> kafkaTemplate;

   /* public ResponseEntity<String> placeOrder(String orderId) {
        logger.info("Order placed successfully for order is: {}", orderId);
        kafkaTemplate.send(TOPIC, orderId);
        return ResponseEntity.ok("Order Placed Successfully!");
    }*/


    public ResponseEntity<String> createOrder(OrderRequest orderRequest) {
        try {
            logger.info("Placing order: {}", orderRequest);

            // Create and populate Order entity
            Order order = new Order();
            order.setOrderNumber(orderRequest.getOrderNumber());
            order.setStatus("PLACED");
            order.setTotalAmount(orderRequest.getTotalAmount());

            // Save order to the database
            order = orderRepository.save(order);
            logger.info("Order saved successfully with ID: {}", order.getId());

            if(order.getId() != null){
                // Publish event to Kafka
                kafkaProducer.sendMessage(TOPIC, orderRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully with ID: " + order.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Failed to publish order for Order ID: " + order.getId());

        } catch (Exception e) {
            logger.error("Error placing order: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order");
        }
    }
}