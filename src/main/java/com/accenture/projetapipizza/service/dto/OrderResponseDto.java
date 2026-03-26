package com.accenture.projetapipizza.service.dto;

import com.accenture.projetapipizza.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderResponseDto(

        UUID id,
        OrderStatus orderStatus,
        List<PizzaResponseDto> pizzas,
        double orderPrice
) {
}
