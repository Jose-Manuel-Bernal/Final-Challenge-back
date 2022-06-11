package com.sofkau.finallChallenge.usecases.provider;

import com.sofkau.finallChallenge.mapper.ProviderMapper;
import com.sofkau.finallChallenge.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProviderUseCase {
    private ProviderRepository repository;
    private ProviderMapper mapper;

    public DeleteProviderUseCase(ProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Void> apply (String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new IllegalStateException("The provider doesn't exist")))
                .flatMap(provider -> repository.deleteById(provider.getId()));
    }
}
