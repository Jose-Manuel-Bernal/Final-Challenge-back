package com.sofkau.finallChallenge.routes.inventory;

import com.sofkau.finallChallenge.dto.InventoryDTO;
import com.sofkau.finallChallenge.entity.Inventory;
import com.sofkau.finallChallenge.usecases.inventory.PutInventoryUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PutInventoryRoute {

    @Bean
    @RouterOperation(path = "/put/inventory"
            , produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT, beanClass = PutInventoryUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "putInventory", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Inventory.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Inventory ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Inventory not found")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Inventory.class))))
    )

    public RouterFunction<ServerResponse> putInventory (PutInventoryUseCase useCase) {
        return route(PUT("/put/inventory").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(InventoryDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(inventoryDTO -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(inventoryDTO))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
