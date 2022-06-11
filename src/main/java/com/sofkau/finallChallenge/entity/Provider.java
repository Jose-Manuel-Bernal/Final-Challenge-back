package com.sofkau.finallChallenge.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "providers")
public class Provider {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
}
