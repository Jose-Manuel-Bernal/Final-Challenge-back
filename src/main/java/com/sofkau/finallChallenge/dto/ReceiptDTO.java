package com.sofkau.finallChallenge.dto;

import com.sofkau.finallChallenge.entity.Product;
import com.sofkau.finallChallenge.entity.Provider;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ReceiptDTO {
    private String id;
    private LocalDate date;
    private List<Product> productList;
    private String providerName;
}
