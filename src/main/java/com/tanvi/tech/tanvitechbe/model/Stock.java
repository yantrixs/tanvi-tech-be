package com.tanvi.tech.tanvitechbe.model;

import java.io.Serializable;

public class Stock extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4479136431803824634L;

    private String userId;
    private String email;
    private String username;
    private String date;
    private String model;
    private String product;
    private int quantity;
    private Number unitRate;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Number getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(Number unitRate) {
        this.unitRate = unitRate;
    }
}
