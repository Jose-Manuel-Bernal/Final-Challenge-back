package com.sofkau.finallChallenge.mapper;

import com.sofkau.finallChallenge.dto.BillDTO;
import com.sofkau.finallChallenge.entity.Bill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class BillMapper {

    private final ModelMapper modelMapper;

    public BillMapper (ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    public BillDTO toBillDTO (Bill bill) {return modelMapper.map(bill, BillDTO.class);}

    public Bill toBIllEntity (BillDTO billDTO) {return modelMapper.map(billDTO, Bill.class);}
}
