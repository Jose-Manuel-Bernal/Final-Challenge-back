package com.sofkau.finallChallenge.routes.receipt;

import com.sofkau.finallChallenge.dto.ProductDTO;
import com.sofkau.finallChallenge.dto.ReceiptDTO;
import com.sofkau.finallChallenge.entity.Product;
import com.sofkau.finallChallenge.entity.Receipt;
import com.sofkau.finallChallenge.usecases.product.PostProductUseCase;
import com.sofkau.finallChallenge.usecases.receipt.PostReceiptUseCase;
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
public class PostReceiptRoute {

    @Bean
    @RouterOperation(path = "/post/receipt", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, beanClass = PostReceiptUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "postReceipt", responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Receipt.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Receipt details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Receipt.class)))
            ))

    public RouterFunction<ServerResponse> postReceipt (PostReceiptUseCase useCase) {
        return route(POST("/post/receipt").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ReceiptDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(receiptDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(receiptDTO))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
