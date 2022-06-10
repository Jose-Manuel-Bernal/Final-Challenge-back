package com.sofkau.finallChallenge.dto;

import com.sofkau.finallChallenge.entity.Product;
import com.sofkau.finallChallenge.entity.Provider;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReceiptDTO {
    private String id;
    private Date date;
    private List<Product> productList;
    private Provider provider;
}
