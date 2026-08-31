package com.bookstore.order.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Long productId;
    private String productName;
    private double price;
    private int quantityToBuy = 1;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        email = v;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double v) {
        price = v;
    }

    public int getQuantityToBuy() {
        return quantityToBuy;
    }

    public void setQuantityToBuy(int v) {
        quantityToBuy = v;
    }
}
