package com.sofkau.finallChallenge.product;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.entity.Product;
import com.sofkau.finallChallenge.entity.Provider;
import com.sofkau.finallChallenge.mapper.ProductMapper;
import com.sofkau.finallChallenge.repository.ProductRepository;
import com.sofkau.finallChallenge.usecases.product.GetAllProductsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class GetAllProductsUseCaseTest {

    private GetAllProductsUseCase useCase;
    @Autowired
    private ProductMapper mapper;
    @Mock
    ProductRepository repository;

    @BeforeEach
    public void setUp() {
        useCase = new GetAllProductsUseCase(repository, mapper);
    }

    @Test
    public void getProductsTest() {
        Product product1 = new Product();
        Product product2 = new Product();
        Provider provider1 = new Provider();
        Provider provider2 = new Provider();
        provider1.setId("01");
        provider1.setName("name1");
        provider1.setPhoneNumber("phone1");
        provider2.setId("02");
        provider2.setName("name2");
        provider2.setPhoneNumber("phone2");
        product1.setId("01");
        product1.setName("name1");
        product1.setPrice(100.0);
        product1.setProvider(provider1);
        product2.setId("02");
        product2.setName("name2");
        product2.setPrice(100.0);
        product2.setProvider(provider2);

        Mockito.when(repository.findAll()).thenReturn(Flux.just(product1, product2));
        Flux<ProductDTO> flux = useCase.apply();

        StepVerifier.create(flux).expectNextCount(2).verifyComplete();

        Mockito.verify(repository).findAll();
    }
}
