package com.example.order.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    private String status;

    public OrderEntity() {}

    public OrderEntity(String product , String status) {

        this.product = product;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getProduct() { return product; }
    public void setId(Long id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
    public void setProduct(String product) { this.product = product; }
}
