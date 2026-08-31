package com.bookstore.user.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "customer_details")
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String addressType;
    private String fullAddress;
    private String city;
    private String state;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        email = v;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String v) {
        addressType = v;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String v) {
        fullAddress = v;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String v) {
        city = v;
    }

    public String getState() {
        return state;
    }

    public void setState(String v) {
        state = v;
    }
}
