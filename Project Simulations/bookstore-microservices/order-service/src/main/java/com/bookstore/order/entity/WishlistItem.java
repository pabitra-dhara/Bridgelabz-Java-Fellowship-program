package com.bookstore.order.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wishlist_items")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Long productId;

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
}
