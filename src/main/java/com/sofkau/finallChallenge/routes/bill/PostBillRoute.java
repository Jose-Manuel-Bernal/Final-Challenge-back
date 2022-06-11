package com.sofkau.finallChallenge.routes.bill;

import com.sofkau.finallChallenge.dto.BillDTO;
import com.sofkau.finallChallenge.entity.Bill;
import com.sofkau.finallChallenge.usecases.bill.PostBillUseCase;
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
public class PostBillRoute {

    @Bean
    @RouterOperation(path = "/post/bill", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = PostBillUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "postBill", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Bill.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Bill details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Bill.class)))
            ))

    public RouterFunction<ServerResponse> postBills (PostBillUseCase useCase) {
        return route(POST("/post/bill").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BillDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(billDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(billDTO))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
