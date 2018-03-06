package com.tanvi.tech.tanvitechbe.model;

import java.io.Serializable;

public class StockOut extends Stock implements Serializable {
    private static final long serialVersionUID = -5438775144360985828L;

    private String stockInId;

    private Number sellPrice;

    private int stockLeft;

    private int sellQuantity;

    public String getStockInId() {
        return stockInId;
    }

    public void setStockInId(String stockInId) {
        this.stockInId = stockInId;
    }

    public Number getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Number sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getStockLeft() {
        return stockLeft;
    }

    public void setStockLeft(int stockLeft) {
        this.stockLeft = stockLeft;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }
}
