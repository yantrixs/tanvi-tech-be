package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.StockIn;
import com.tanvi.tech.tanvitechbe.model.StockOut;
import com.tanvi.tech.tanvitechbe.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService implements IStockService {

    private final StockRepository repository;

    @Autowired
    public StockService(final StockRepository stockRepository) {
        this.repository = stockRepository;
    }

    @Override
    public List<StockIn> findAll() {
        return repository.findAll();
    }

    @Override
    public StockIn update(String id, StockIn stock) {
        stock.setId(id);

        final StockIn saved = repository.findOne(id);

        if (saved != null) {
            stock.setCreatedAt(saved.getCreatedAt());
            stock.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            stock.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(stock);
        return stock;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public List<StockIn> create(List<StockIn> stocks) {
        for (StockIn stock : stocks) {
            stock.setStockLeft(stock.getQuantity());
            stock.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        return repository.save(stocks);
    }

    @Override
    public StockIn find(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<StockIn> updateStocks(List<StockOut> stocks) {
        List<StockIn> updatedStock = new ArrayList<>();
        for (StockOut stock : stocks) {
            StockIn stockInfo = repository.findOne(stock.getId());
            int updateStockNo = stockInfo.getStockLeft() - stock.getSellQuantity();
            stockInfo.setStockLeft(updateStockNo);
            stockInfo.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            // stock.setUnitRate(stockInfo.getUnitRate());
            updatedStock.add(stockInfo);
        }

        repository.save(updatedStock);
        return updatedStock;
    }
}
