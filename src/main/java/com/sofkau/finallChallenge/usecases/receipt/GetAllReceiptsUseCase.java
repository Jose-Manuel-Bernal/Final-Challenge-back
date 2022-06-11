package com.sofkau.finallChallenge.usecases.receipt;

import com.sofkau.finallChallenge.dto.ReceiptDTO;
import com.sofkau.finallChallenge.mapper.ReceiptMapper;
import com.sofkau.finallChallenge.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllReceiptsUseCase {
    private ReceiptRepository repository;
    private ReceiptMapper mapper;

    public GetAllReceiptsUseCase(ReceiptRepository repository, ReceiptMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<ReceiptDTO> apply () {return repository.findAll().map(receipt -> mapper.toReceiptDTO(receipt));}
}
