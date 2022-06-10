package com.sofkau.finallChallenge.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Bill {

    @Id
    private String id;
    private LocalDate date;
    private String clientName;
    private String sellerName;
    private Double price;
    private List<Product> productList;
}
