package com.sofkau.finallChallenge.routes.provider;

import com.sofkau.finallChallenge.dto.ProviderDTO;
import com.sofkau.finallChallenge.entity.Provider;
import com.sofkau.finallChallenge.usecases.provider.PostProviderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostProviderRoute {

    @Bean
    @RouterOperation(path = "/post/provider", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = PostProviderUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "postProvider", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Provider.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Product details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Provider.class)))
            ))

    public RouterFunction<ServerResponse> postProvider(PostProviderUseCase useCase) {
        return route(POST("/post/provider").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(providerDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(providerDTO))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}