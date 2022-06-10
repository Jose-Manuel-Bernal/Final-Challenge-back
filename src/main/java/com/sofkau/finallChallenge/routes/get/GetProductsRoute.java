package com.sofkau.finallChallenge.routes.get;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.usecases.get.GetAllProductsUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetProductsRoute {

    public RouterFunction<ServerResponse> getAllProducts (GetAllProductsUseCase useCase) {
        return route(GET("/get/products"),
                request -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(useCase.apply(), ProductDTO.class)));
    }
}
