package com.sofkau.finallChallenge.receipt;

import com.sofkau.finallChallenge.dto.ReceiptDTO;
import com.sofkau.finallChallenge.entity.Product;
import com.sofkau.finallChallenge.entity.Provider;
import com.sofkau.finallChallenge.entity.Receipt;
import com.sofkau.finallChallenge.mapper.ReceiptMapper;
import com.sofkau.finallChallenge.repository.ReceiptRepository;
import com.sofkau.finallChallenge.usecases.receipt.GetAllReceiptsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GetAllReceiptsUseCaseTest {
    private GetAllReceiptsUseCase useCase;
    @Autowired
    private ReceiptMapper mapper;
    @Mock
    ReceiptRepository repository;

    @BeforeEach
    void setUp() {
        useCase = new GetAllReceiptsUseCase(repository, mapper);
    }

    @Test
    public void getReceiptsTest() {

        Product product1 = new Product();
        Product product2 = new Product();
        Provider provider1 = new Provider();
        Provider provider2 = new Provider();
        Receipt receipt1 = new Receipt();
        Receipt receipt2 = new Receipt();
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
        List<Product> listproducts = new ArrayList<>();
        listproducts.add(product1);
        listproducts.add(product2);
        receipt1.setId("01");
        receipt1.setDate(LocalDate.of(2020, 1, 8));
        receipt1.setProductList(listproducts);
        receipt1.setProviderName("providerName1");
        receipt2.setId("02");
        receipt2.setDate(LocalDate.of(2021, 1, 8));
        receipt2.setProductList(listproducts);
        receipt2.setProviderName("providerName2");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(receipt1, receipt2));
        Flux<ReceiptDTO> flux = useCase.apply();

        StepVerifier.create(flux).expectNextCount(2).verifyComplete();

        Mockito.verify(repository).findAll();
    }
}
