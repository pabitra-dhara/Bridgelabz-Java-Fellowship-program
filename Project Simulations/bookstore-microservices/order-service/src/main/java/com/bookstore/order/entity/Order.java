package com.bookstore.order.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private double totalAmount;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        email = v;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double v) {
        totalAmount = v;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
