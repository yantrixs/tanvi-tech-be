package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.SellStock;
import com.tanvi.tech.tanvitechbe.model.StockOut;
import com.tanvi.tech.tanvitechbe.model.Stock;
import com.tanvi.tech.tanvitechbe.repository.SellStockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellService implements ISellService{

    private final SellStockRepository repository;
    private final StockService stockService;

    public SellService(final SellStockRepository sellStockRepository,
                       final StockService stockService) {
        this.repository = sellStockRepository;
        this.stockService = stockService;
    }

    @Override
    public List<SellStock> findAll() {
        return repository.findAll();
    }

    @Override
    public SellStock update(String id, SellStock stock) {
        stock.setId(id);

        final SellStock saved = repository.findOne(id);

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
        repository.delete(id);
        return id;
    }

    @Override
    public SellStock create(SellStock stocksOut) {
        List<StockOut> stocks = new ArrayList<StockOut>();
        for (StockOut stock : stocksOut.getStockOuts()){
            stock.setCreatedAt(String.valueOf(LocalDateTime.now()));
            stocks.add(stock);
        }
        stocksOut.setStockOuts(stocks);
        SellStock stockOut = repository.save(stocksOut);
        stockService.updateStocks(stocks);
        return repository.save(stocksOut);
    }

    @Override
    public SellStock find(String id) {
        return repository.findOne(id);
    }
}
