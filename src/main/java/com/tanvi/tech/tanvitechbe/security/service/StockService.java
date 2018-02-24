package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.Stock;
import com.tanvi.tech.tanvitechbe.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService implements IStockService {

    private final StockRepository repository;

    @Autowired
    public StockService(final StockRepository stockRepository) {
        this.repository = stockRepository;
    }

    @Override
    public List<Stock> findAll() {
        return repository.findAll();
    }

    @Override
    public Stock update(String id, Stock stock) {
        stock.setId(id);

        final Stock saved = repository.findOne(id);

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
    public List<Stock> create(List<Stock> stocks) {
        for (Stock stock : stocks){
            stock.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        return repository.save(stocks);
    }

    @Override
    public Stock find(String id) {
        return repository.findOne(id);
    }
}
