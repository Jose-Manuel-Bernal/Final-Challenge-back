package com.sofkau.finallChallenge.repository;

import com.sofkau.finallChallenge.entity.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends ReactiveMongoRepository<Provider, String> {
}
