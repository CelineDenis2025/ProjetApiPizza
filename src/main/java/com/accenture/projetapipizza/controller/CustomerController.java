package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.service.CustomerService;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Slf4j
@RestController
@AllArgsConstructor
public class CustomerController implements CustomerApi{

    private final CustomerService customerService;

    @Override
    public ResponseEntity<Void> addCustomer(@Valid CustomerRequestDto customerRequestDto) {
        CustomerResponseDto responseDto = customerService.addCustomer(customerRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
