package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;

public interface OrderService {

    /**
     * Adds a new order after validating the provided data.
     *
     * @param orderRequestDto the information of the order to create
     * @return the created order as a OrderResponseDto
     * @throws OrderException if the provided data is invalid
     */
    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);

    /**
     * Checks whether the customer data is valid.
     *
     * @param orderRequestDto the order information to validate
     * @throws OrderException if a field is missing
     */
    void validateOrderRequest(OrderRequestDto orderRequestDto);
}
