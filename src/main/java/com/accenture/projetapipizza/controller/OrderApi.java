package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Orders", description = "Order management API")
@RequestMapping("/orders")
public interface OrderApi {

    @Operation(summary = "Add a new order")
    @ApiResponse(responseCode = "201", description = "Created order")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = Error.class)))
    @PostMapping
    ResponseEntity<Void> addOrder(@RequestBody OrderRequestDto orderRequestDto);
}
