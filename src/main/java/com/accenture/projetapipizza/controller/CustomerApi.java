package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Customers", description = "Customer management API")
@RequestMapping("/customers")
public interface CustomerApi {

    @Operation(summary = "Add a new customer")
    @ApiResponse(responseCode = "201", description = "Created customer")
    @ApiResponse(responseCode = "400", description = "Invalid request",
    content = @Content(schema = @Schema(implementation = Error.class)))
    @PostMapping
    ResponseEntity<Void> addCustomer(@RequestBody CustomerRequestDto customerRequestDto);

}
