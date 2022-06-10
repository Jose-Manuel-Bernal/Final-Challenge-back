package com.sofkau.finallChallenge.usecases.post;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.mapper.ProductMapper;
import com.sofkau.finallChallenge.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostProductUseCase {
    private ProductRepository repository;
    private ProductMapper mapper;

    public PostProductUseCase(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<ProductDTO> apply (ProductDTO productDTO) {
        return repository.save(mapper.toProductEntity(productDTO)).map(product -> mapper.toProductDTO(product));
    }
}
