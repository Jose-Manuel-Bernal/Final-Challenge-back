package com.sofkau.finallChallenge.repository;

import com.sofkau.finallChallenge.entity.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends ReactiveMongoRepository<Receipt, String> {
}
