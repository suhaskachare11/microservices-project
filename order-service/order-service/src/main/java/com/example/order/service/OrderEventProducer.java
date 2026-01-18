package com.example.order.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderEvent(String product) {

        String message = "OrderCreatedEvent for product: " + product;

        System.out.println("Order Service: Sending Kafka event -> " + message);

        kafkaTemplate.send("order-created-topic", message);
    }

    public void publishOrderCreated(Long orderId) {

        String msg = "ORDER_CREATED:" + orderId;

        kafkaTemplate.send("order-saga-topic", msg);

        System.out.println("Order Created Event Sent: " + msg);
    }

}

