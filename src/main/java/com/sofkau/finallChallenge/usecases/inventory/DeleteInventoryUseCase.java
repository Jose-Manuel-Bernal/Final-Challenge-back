package com.sofkau.finallChallenge.usecases.inventory;

import com.sofkau.finallChallenge.mapper.InventoryMapper;
import com.sofkau.finallChallenge.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteInventoryUseCase {
    private InventoryRepository repository;
    private InventoryMapper mapper;

    public DeleteInventoryUseCase(InventoryRepository repository, InventoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Void> apply (String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new IllegalStateException("The inventory doesn't exist")))
                .flatMap(inventory -> repository.deleteById(inventory.getId()));
    }
}
