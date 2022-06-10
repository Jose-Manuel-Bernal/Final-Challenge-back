package com.sofkau.finallChallenge.dto;

import com.sofkau.finallChallenge.entity.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BillDTO {
    private String id;
    private LocalDate date;
    private String clientName;
    private String sellerName;
    private Double price;
    private List<Product> productList;
}
