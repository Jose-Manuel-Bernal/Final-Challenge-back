package com.sofkau.finallChallenge.routes.inventory;

import com.sofkau.finallChallenge.usecases.inventory.DeleteInventoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteInventoryRoute {

    @Bean
    @RouterOperation(path = "/delete/inventory/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteInventoryUseCase.class, beanMethod = "apply"
            , operation = @Operation(operationId = "deleteInventory", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Inventory ID supplied"),
            @ApiResponse(responseCode = "404", description = "Inventory not found")}, parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id")}
    ))

    public RouterFunction<ServerResponse> deleteInventory (DeleteInventoryUseCase useCAse) {
        return route(DELETE("/delete/inventory/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCAse.apply(request.pathVariable("id"))
                        .flatMap(unused -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
