package com.sofkau.finallChallenge.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "receipts")
public class Receipt {

    @Id
    private String id;
    private LocalDate date;
    private Product product;
    private Integer amount;
}
