package com.sofkau.finallChallenge.routes.provider;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.entity.Provider;
import com.sofkau.finallChallenge.usecases.provider.GetAllProvidersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetProvidersRoute {

    @Bean
    @RouterOperation(path = "/get/providers", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllProvidersUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "geAllProviders", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Provider.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Provider details supplied")}
            ))

    public RouterFunction<ServerResponse> getAllProviders (GetAllProvidersUseCase useCase) {
        return route(GET("/get/providers"),
                request -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(useCase.apply(), ProductDTO.class)));
    }
}
