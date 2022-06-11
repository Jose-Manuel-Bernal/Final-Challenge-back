package com.sofkau.finallChallenge.routes.bill;

import com.sofkau.finallChallenge.dto.BillDTO;
import com.sofkau.finallChallenge.entity.Bill;
import com.sofkau.finallChallenge.usecases.bill.GetAllBillsUseCase;
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
public class GetBillsRoute {

    @Bean
    @RouterOperation(path = "/get/bills", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllBillsUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "geAllBills", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Bill.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Bills details supplied")}
            ))

    public RouterFunction<ServerResponse> getAllBills (GetAllBillsUseCase useCase) {
        return route(GET("/get/bills"),
                request -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(useCase.apply(), BillDTO.class)));
    }
}
