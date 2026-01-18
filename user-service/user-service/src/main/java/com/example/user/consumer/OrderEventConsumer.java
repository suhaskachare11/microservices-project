package com.example.user.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @KafkaListener(topics = "order-created-topic", groupId = "user-service-group")
    public void consume(String message) {

        System.out.println("User Service: Received Kafka event -> " + message);
    }
}

