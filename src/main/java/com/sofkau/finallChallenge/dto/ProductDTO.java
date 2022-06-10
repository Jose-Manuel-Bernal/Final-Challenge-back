package com.sofkau.finallChallenge.dto;

import com.sofkau.finallChallenge.entity.Provider;
import lombok.Data;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private Double price;
    private Provider provider;
}
