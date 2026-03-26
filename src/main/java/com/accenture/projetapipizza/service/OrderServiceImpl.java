package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.OrderException;
import com.accenture.projetapipizza.mapper.OrderMapper;
import com.accenture.projetapipizza.model.Order;
import com.accenture.projetapipizza.repository.OrderDao;
import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;
import com.accenture.projetapipizza.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final MessageSourceAccessor messages;


    /** {@inheritDoc} */
    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        validateOrderRequest(orderRequestDto);
        log.info("Validating and creating order");
        Order saved = orderDao.save(orderMapper.toOrder(orderRequestDto));
        log.info("Order '{}' successfully created with id={}", saved.getId());
        return orderMapper.toOrderResponseDto(saved);
    }


    public void validateOrderRequest(OrderRequestDto orderRequestDto) {
        if (orderRequestDto == null) {
            throw new OrderException(messages.getMessage(Messages.CUSTOMER_NOT_NULL));
        }
        if (orderRequestDto.orderStatus() == null) {
            throw new OrderException(messages.getMessage(Messages.ORDER_STATUS_NOT_NULL));
        }
        if (orderRequestDto.pizzaNames() == null ||  orderRequestDto.pizzaNames().isEmpty()) {
            throw new OrderException(messages.getMessage(Messages.ORDER_LIST_PIZZA_NAME_NOT_EMPTY));
        }
        if (orderRequestDto.customerEmail() == null) {
            throw new OrderException(messages.getMessage(Messages.ORDER_CUSTOMER_EMAIL_NOT_NULL));
        }
    }
}
