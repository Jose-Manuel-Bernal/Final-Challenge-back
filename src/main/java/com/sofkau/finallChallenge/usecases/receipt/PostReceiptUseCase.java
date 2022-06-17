package com.sofkau.finallChallenge.usecases.receipt;

import com.sofkau.finallChallenge.dto.ReceiptDTO;
import com.sofkau.finallChallenge.mapper.ReceiptMapper;
import com.sofkau.finallChallenge.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PostReceiptUseCase {
    private ReceiptRepository repository;
    private ReceiptMapper mapper;

    public PostReceiptUseCase(ReceiptRepository repository, ReceiptMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<ReceiptDTO> apply (ReceiptDTO receiptDTO) {
        receiptDTO.setDate(LocalDate.now());
        return repository.save(mapper.toReceiptEntity(receiptDTO)).map(receipt -> mapper.toReceiptDTO(receipt));
    }
}
