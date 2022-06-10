package com.sofkau.finallChallenge.usecases.post;

import com.sofkau.finallChallenge.dto.ProviderDTO;
import com.sofkau.finallChallenge.mapper.ProviderMapper;
import com.sofkau.finallChallenge.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostProviderUseCase {
    private ProviderRepository repository;
    private ProviderMapper mapper;

    public PostProviderUseCase(ProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<ProviderDTO> apply (ProviderDTO providerDTO) {
        return repository.save(mapper.toProviderEntity(providerDTO)).map(provider -> mapper.toProviderDTO(provider));
    }
}
