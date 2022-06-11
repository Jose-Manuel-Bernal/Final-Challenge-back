package com.sofkau.finallChallenge.routes.inventory;

import com.sofkau.finallChallenge.entity.Inventory;
import com.sofkau.finallChallenge.usecases.inventory.GetAllInventoriesUseCase;
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
public class GetInventoriesRoute {

    @Bean

    @RouterOperation(path = "/get/inventories", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllInventoriesUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "geAllInventories", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Inventory.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Inventories details supplied")}
            ))

    public RouterFunction<ServerResponse> getAllInventories (GetAllInventoriesUseCase useCase) {
        return route(GET("/get/inventories"),
                request -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(useCase.apply(), Inventory.class)));
    }
}
