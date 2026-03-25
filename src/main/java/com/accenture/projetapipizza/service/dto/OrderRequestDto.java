package com.accenture.projetapipizza.service.dto;

import com.accenture.projetapipizza.model.OrderStatus;
import com.accenture.projetapipizza.utils.Messages;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

public record OrderRequestDto(

        @NotNull(message = Messages.ORDER_STATUS_NOT_NULL)
        OrderStatus orderStatus,

        @NotEmpty(message = Messages.ORDER_PIZZA_LIST_NOT_EMPTY)
        List<UUID> pizzaIds

) {
}
