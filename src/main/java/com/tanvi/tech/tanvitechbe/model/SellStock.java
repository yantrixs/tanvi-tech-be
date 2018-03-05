package com.tanvi.tech.tanvitechbe.model;

import java.io.Serializable;
import java.util.List;

public class SellStock extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5438775144360985828L;
    private String userId;
    private String email;
    private String username;
    private List<Stock> stocks;
    private Address address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStock(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
