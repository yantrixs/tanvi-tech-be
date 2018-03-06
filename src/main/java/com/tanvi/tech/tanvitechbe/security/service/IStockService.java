package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.Stock;
import com.tanvi.tech.tanvitechbe.model.StockIn;
import com.tanvi.tech.tanvitechbe.model.StockOut;

import java.util.List;

public interface IStockService {

    List<StockIn> findAll();

    StockIn update(String id, StockIn stock);

    String delete(String id);

    List<StockIn> create(List<StockIn> stocks);

    StockIn find(String id);

    List<StockIn> updateStocks(List<StockOut> stocks);
}
