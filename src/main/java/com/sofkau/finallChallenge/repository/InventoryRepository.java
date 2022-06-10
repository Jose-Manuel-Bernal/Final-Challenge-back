package com.sofkau.finallChallenge.repository;

import com.sofkau.finallChallenge.entity.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends ReactiveMongoRepository<Inventory, String> {
}
