package com.sofkau.finallChallenge.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "receipts")
public class Receipt {

    @Id
    private String id;
    private LocalDate date;
    private List<Product> productList;
    private String providerName;
}
