package com.tanvi.tech.tanvitechbe.model;

import java.io.Serializable;

public class StockIn extends Stock implements Serializable {

    private static final long serialVersionUID = -917036871012946396L;

    private int quantity;
    private Number unitRate;
    private int stockLeft;

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

    public int getStockLeft() {
        return stockLeft;
    }

    public void setStockLeft(int stockLeft) {
        this.stockLeft = stockLeft;
    }
}
