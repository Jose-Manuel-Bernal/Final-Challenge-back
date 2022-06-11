package com.sofkau.finallChallenge.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "inventories")
public class Inventory {

    @Id
    private String id;
    private Integer max;
    private Integer min;
    private Integer stock;
    private Product product;
}
