package com.example.order.service;

import com.example.order.client.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final UserClient userClient;

    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }

    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "userFallback")
    public String callUserService() {

        System.out.println("Order Service: Calling User Service");

        return userClient.callUserHello();
    }

    // 🔥 FALLBACK METHOD
    public String userFallback(Throwable ex) {

        System.out.println("Fallback executed due to: " + ex.getMessage());

        return "User Service is down. Default response from Order Service.";
    }
}

