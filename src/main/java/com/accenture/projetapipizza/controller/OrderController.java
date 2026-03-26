package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.service.OrderService;
import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;
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
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<Void> addOrder(@Valid OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.addOrder(orderRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderResponseDto.id())
                .toUri();
        return  ResponseEntity.created(location).build();
    }

}
