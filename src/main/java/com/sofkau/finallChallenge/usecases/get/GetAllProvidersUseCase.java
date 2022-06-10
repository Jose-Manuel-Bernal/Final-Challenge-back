package com.sofkau.finallChallenge.usecases.get;

import com.sofkau.finallChallenge.dto.ProviderDTO;
import com.sofkau.finallChallenge.mapper.ProviderMapper;
import com.sofkau.finallChallenge.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllProvidersUseCase {
    private ProviderRepository repository;
    private ProviderMapper mapper;

    public GetAllProvidersUseCase(ProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<ProviderDTO> apply () {
        return repository.findAll().map(provider -> mapper.toProviderDTO(provider));
    }
}
