package com.sofkau.finallChallenge.usecases.get;

import com.sofkau.finallChallenge.dto.BillDTO;
import com.sofkau.finallChallenge.mapper.BillMapper;
import com.sofkau.finallChallenge.repository.BillRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllBillsUseCase {
    private BillRepository repository;
    private BillMapper mapper;

    public GetAllBillsUseCase(BillRepository repository, BillMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<BillDTO> apply () {return repository.findAll().map(bill -> mapper.toBillDTO(bill));}
}
