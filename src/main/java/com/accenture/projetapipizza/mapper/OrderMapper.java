package com.accenture.projetapipizza.mapper;

import com.accenture.projetapipizza.model.Order;
import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto toOrderResponseDto(Order order);
}
