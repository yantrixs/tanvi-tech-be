package com.tanvi.tech.tanvitechbe.repository;

import com.tanvi.tech.tanvitechbe.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
}
