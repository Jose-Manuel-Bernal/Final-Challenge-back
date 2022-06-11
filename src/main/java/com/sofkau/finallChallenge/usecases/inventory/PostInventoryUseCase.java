package com.sofkau.finallChallenge.usecases.inventory;

import com.sofkau.finallChallenge.dto.InventoryDTO;
import com.sofkau.finallChallenge.mapper.InventoryMapper;
import com.sofkau.finallChallenge.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostInventoryUseCase {
    private InventoryRepository repository;
    private InventoryMapper mapper;

    public PostInventoryUseCase(InventoryRepository repository, InventoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<InventoryDTO> apply (InventoryDTO inventoryDTO) {
        return repository.save(mapper.toInventoryEntity(inventoryDTO)).map(inventory -> mapper.toInventoryDTO(inventory));
    }
}
