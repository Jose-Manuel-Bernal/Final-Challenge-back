package com.sofkau.finallChallenge.usecases.inventory;

import com.sofkau.finallChallenge.dto.InventoryDTO;
import com.sofkau.finallChallenge.mapper.InventoryMapper;
import com.sofkau.finallChallenge.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllInventoriesUseCase {
    private InventoryRepository repository;
    private InventoryMapper mapper;

    public GetAllInventoriesUseCase(InventoryRepository repository, InventoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<InventoryDTO> apply () {return repository.findAll().map(inventory -> mapper.toInventoryDTO(inventory));}
}
