package com.tanvi.tech.tanvitechbe.repository;

import com.tanvi.tech.tanvitechbe.model.SellStock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellStockRepository extends MongoRepository<SellStock, String> {
}
