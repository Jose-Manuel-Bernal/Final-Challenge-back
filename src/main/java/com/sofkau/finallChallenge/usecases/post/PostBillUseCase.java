package com.sofkau.finallChallenge.usecases.post;

import com.sofkau.finallChallenge.dto.BillDTO;
import com.sofkau.finallChallenge.mapper.BillMapper;
import com.sofkau.finallChallenge.repository.BillRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PostBillUseCase {
    private BillRepository repository;
    private BillMapper mapper;

    public PostBillUseCase(BillRepository repository, BillMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<BillDTO> apply (BillDTO billDTO) {
        billDTO.setDate(LocalDate.now());
        return repository.save(mapper.toBIllEntity(billDTO)).map(bill -> mapper.toBillDTO(bill));
    }
}
