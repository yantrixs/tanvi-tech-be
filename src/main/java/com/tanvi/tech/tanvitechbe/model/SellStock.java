package com.tanvi.tech.tanvitechbe.model;

import java.io.Serializable;
import java.util.List;

public class SellStock extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2328115198610042907L;
    private String userId;
    private String email;
    private String username;
    private List<StockOut> stockOuts;
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

    public List<StockOut> getStockOuts() {
        return stockOuts;
    }

    public void setStockOuts(List<StockOut> stockOuts) {
        this.stockOuts = stockOuts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
