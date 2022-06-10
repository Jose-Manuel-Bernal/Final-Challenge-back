package com.sofkau.finallChallenge.usecases.delete;

import com.sofkau.finallChallenge.mapper.ProductMapper;
import com.sofkau.finallChallenge.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProductUseCAse {
    private ProductRepository repository;
    private ProductMapper mapper;

    public DeleteProductUseCAse(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Void> apply (String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new IllegalStateException("The preoduct doesn't exist")))
                .flatMap(product -> repository.deleteById(product.getId()));
    }
}
