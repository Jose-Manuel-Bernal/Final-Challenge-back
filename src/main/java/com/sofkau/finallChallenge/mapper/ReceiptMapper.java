package com.sofkau.finallChallenge.mapper;

import com.sofkau.finallChallenge.dto.ReceiptDTO;
import com.sofkau.finallChallenge.entity.Receipt;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class ReceiptMapper {

    private final ModelMapper modelMapper;

    public ReceiptMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReceiptDTO toReceiptDTO (Receipt receipt) {return modelMapper.map(receipt, ReceiptDTO.class);}

    public Receipt toReceiptEntity (ReceiptDTO receiptDTO) {return  modelMapper.map(receiptDTO, Receipt.class);}
}
