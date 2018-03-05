package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.Stock;

import java.util.List;

public interface IStockService {

    List<Stock> findAll();

    Stock update(String id, Stock stock);

    String delete(String id);

    List<Stock> create(List<Stock> stocks);

    Stock find(String id);

    List<Stock> updateStocks(List<Stock> stocks);
}
