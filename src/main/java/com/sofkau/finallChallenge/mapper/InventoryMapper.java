package com.sofkau.finallChallenge.mapper;

import com.sofkau.finallChallenge.dto.InventoryDTO;
import com.sofkau.finallChallenge.entity.Inventory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class InventoryMapper {

    private final ModelMapper modelMapper;

    public InventoryMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    public InventoryDTO toInventoryDTO(Inventory inventory) {return modelMapper.map(inventory, InventoryDTO.class);}

    public Inventory toInventoryEntity(InventoryDTO inventoryDTO){
        return modelMapper.map(inventoryDTO, Inventory.class);
    }
}
