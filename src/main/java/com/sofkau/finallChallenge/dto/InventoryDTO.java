package com.sofkau.finallChallenge.dto;

import com.sofkau.finallChallenge.entity.Product;
import lombok.Data;

@Data
public class InventoryDTO {

    private String id;
    private Integer max;
    private Integer min;
    private Integer stock;
    private Product product;
}
