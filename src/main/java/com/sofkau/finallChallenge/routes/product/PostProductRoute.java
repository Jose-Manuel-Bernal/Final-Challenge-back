package com.sofkau.finallChallenge.routes.product;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.usecases.product.PostProductUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostProductRoute {

    private RouterFunction<ServerResponse> postProduct (PostProductUseCase useCase) {
        return route(POST("/post/product").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(productDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(productDTO))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}