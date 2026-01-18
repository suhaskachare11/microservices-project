package com.example.user.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSagaConsumer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserSagaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-saga-topic", groupId = "user-saga-group")
    public void consume(String msg) {

        System.out.println("User Service received: " + msg);

        if (msg.startsWith("ORDER_CREATED")) {

            // simulate failure
            boolean success = true;

            if (success) {
                System.out.println("User update success");
            } else {
                System.out.println("User update failed, sending compensation");

                // send failure event
                kafkaTemplate.send("order-saga-topic", "USER_UPDATE_FAILED:" + msg.split(":")[1]);
            }
        }
    }
}

