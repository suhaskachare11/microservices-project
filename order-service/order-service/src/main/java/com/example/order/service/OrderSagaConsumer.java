package com.example.order.service;

import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderSagaConsumer {

    private final OrderRepository repo;

    public OrderSagaConsumer(OrderRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "order-saga-topic", groupId = "order-saga-group")
    public void consume(String msg) {

        if (msg.startsWith("USER_UPDATE_FAILED")) {

            Long orderId = Long.parseLong(msg.split(":")[1]);

            System.out.println("Compensating order: " + orderId);

            OrderEntity order = repo.findById(orderId).orElseThrow();
            order.setStatus("CANCELLED");
            repo.save(order);
        }
    }
}

