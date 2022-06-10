package com.sofkau.finallChallenge.mapper;

import com.sofkau.finallChallenge.dto.ProviderDTO;
import com.sofkau.finallChallenge.entity.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProviderMapper {

    private final ModelMapper modelMapper;

    public ProviderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProviderDTO toProviderDTO(Provider provider){return modelMapper.map(provider, ProviderDTO.class);}

    public Provider toProviderEntity(ProviderDTO providerDTO){return modelMapper.map(providerDTO, Provider.class);}
}
