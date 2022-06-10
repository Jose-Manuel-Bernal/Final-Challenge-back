package com.sofkau.finallChallenge.usecases.get;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.mapper.ProductMapper;
import com.sofkau.finallChallenge.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllProductsUseCase {
    private ProductRepository repository;
    private ProductMapper mapper;

    public GetAllProductsUseCase(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<ProductDTO> apply () {return repository.findAll().map(product -> mapper.toProductDTO(product));}
}
