package com.example.order.controller;

import com.example.order.client.UserClient;
import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderEventProducer;
import com.example.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository repo;
    private final OrderEventProducer producer;
    private final OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderRepository repo,
                           OrderEventProducer producer,
                           OrderService orderService) {
        this.repo = repo;
        this.producer = producer;
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public String createOrder(@RequestParam String product) {

        log.info("Create order request received. product={}", product);

        // SYNC CALL TO USER SERVICE
        String userResponse = orderService.callUserService();
        System.out.println("Order Service: Response from User Service = " + userResponse);

        OrderEntity order = new OrderEntity(product, "PENDING");
        repo.save(order);


        // 🔥 ASYNC KAFKA EVENT
        producer.publishOrderEvent(product);

        producer.publishOrderCreated(order.getId());

        return "Order created for product: " + product;
    }
}
