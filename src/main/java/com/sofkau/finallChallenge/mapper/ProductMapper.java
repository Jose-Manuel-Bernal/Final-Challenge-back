package com.sofkau.finallChallenge.mapper;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO toProductDTO(Product product){ return modelMapper.map(product, ProductDTO.class);}

    public Product toProductEntity(ProductDTO productDTO){return modelMapper.map(productDTO, Product.class);}
}
