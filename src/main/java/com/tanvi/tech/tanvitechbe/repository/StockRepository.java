package com.tanvi.tech.tanvitechbe.repository;

import com.tanvi.tech.tanvitechbe.model.StockIn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<StockIn, String> {
}
