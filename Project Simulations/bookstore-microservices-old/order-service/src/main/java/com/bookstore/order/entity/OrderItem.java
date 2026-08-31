package com.bookstore.order.entity;

import jakarta.persistence.*;


@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private int productQuantity;
    private double productPrice;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long v) {
        productId = v;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String v) {
        productName = v;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int v) {
        productQuantity = v;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double v) {
        productPrice = v;
    }
}
