package com.sofkau.finallChallenge.provider;

import com.sofkau.finallChallenge.dto.ProviderDTO;
import com.sofkau.finallChallenge.entity.Provider;
import com.sofkau.finallChallenge.mapper.ProviderMapper;
import com.sofkau.finallChallenge.repository.ProviderRepository;
import com.sofkau.finallChallenge.usecases.provider.GetAllProvidersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GetAllProvidersUseCaseTest {
    private GetAllProvidersUseCase useCase;
    @Autowired
    private ProviderMapper mapper;
    @Mock
    ProviderRepository repository;

    @BeforeEach
    void setUp() {
        useCase = new GetAllProvidersUseCase(repository, mapper);
    }

    @Test
    public void getProvidersTest() {

        Provider provider1 = new Provider();
        Provider provider2 = new Provider();
        Provider provider3 = new Provider();
        provider1.setId("01");
        provider1.setName("name1");
        provider1.setPhoneNumber("phone1");
        provider2.setId("02");
        provider2.setName("name2");
        provider2.setPhoneNumber("phone2");
        provider3.setId("03");
        provider3.setName("name3");
        provider3.setPhoneNumber("phone3");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(provider1, provider2, provider3));
        Flux<ProviderDTO> flux = useCase.apply();

        StepVerifier.create(flux).expectNextCount(3).verifyComplete();

        Mockito.verify(repository).findAll();
    }
}
