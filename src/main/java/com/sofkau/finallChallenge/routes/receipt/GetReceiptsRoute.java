package com.sofkau.finallChallenge.routes.receipt;

import com.sofkau.finallChallenge.dto.ReceiptDTO;
import com.sofkau.finallChallenge.entity.Receipt;
import com.sofkau.finallChallenge.usecases.receipt.GetAllReceiptsUseCase;
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
public class GetReceiptsRoute {

    @Bean
    @RouterOperation(path = "/get/receipts", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllReceiptsUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "geAllReceipts", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Receipt.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Receipts details supplied")}
            ))

    public RouterFunction<ServerResponse> getAllReceipts (GetAllReceiptsUseCase useCase) {
        return route(GET("/get/receipts"),
                request -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(useCase.apply(), ReceiptDTO.class)));
    }
}
