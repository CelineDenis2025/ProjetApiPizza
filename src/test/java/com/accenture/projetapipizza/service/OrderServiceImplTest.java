package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.OrderException;
import com.accenture.projetapipizza.mapper.OrderMapper;
import com.accenture.projetapipizza.model.*;
import com.accenture.projetapipizza.repository.OrderDao;
import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.utils.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderService orderService;

    @Mock
    private MessageSourceAccessor messages;

    @BeforeEach
    void setUp() {
        orderDao = mock(OrderDao.class);
        orderMapper = mock(OrderMapper.class);
        messages = mock(MessageSourceAccessor.class);
        orderService = new OrderServiceImpl(orderDao, orderMapper, messages);
    }


    @Test
    @DisplayName("Test when order is null")
    void addOrderNull() {
        OrderException exception = Assertions.assertThrows(OrderException.class, () -> orderService.addOrder(null));

        Assertions.assertEquals(messages.getMessage(Messages.ORDER_NOT_NULL), exception.getMessage());
    }

    @Test
    @DisplayName("Test when order status is null")
    void addOrderNullStatus() {
        List<String> pizzaNames = List.of("Margherita");
        OrderRequestDto dto = new OrderRequestDto(null, pizzaNames, "john.doe@gmail.com");

        OrderException exception = Assertions.assertThrows(OrderException.class, () -> orderService.addOrder(dto));

        Assertions.assertEquals(messages.getMessage(Messages.ORDER_STATUS_NOT_NULL), exception.getMessage());
    }

    @Test
    @DisplayName("Test when the list of pizza is empty")
    void addOrderEmptyListOfPizza() {
        List<String> pizzaNames = List.of();
        OrderRequestDto dto = new OrderRequestDto(OrderStatus.PENDING, pizzaNames, "john.doe@gmail.com");

        OrderException exception = Assertions.assertThrows(OrderException.class, () -> orderService.addOrder(dto));

        Assertions.assertEquals(messages.getMessage(Messages.ORDER_LIST_PIZZA_NAME_NOT_EMPTY), exception.getMessage());
    }

    @Test
    @DisplayName("Test when the email of the customer is null")
    void addOrderNullCustomerEmail() {
        List<String> pizzaNames = List.of("Margherita");
        OrderRequestDto dto = new OrderRequestDto(OrderStatus.PENDING, pizzaNames, null);

        OrderException exception = Assertions.assertThrows(OrderException.class, () -> orderService.addOrder(dto));

        Assertions.assertEquals(messages.getMessage(Messages.ORDER_CUSTOMER_EMAIL_NOT_NULL), exception.getMessage());
    }


    @Test
    @DisplayName("Test when order object is well persisted from valid inputs")
    void addCustomerValidInput(){
        OrderService spy = Mockito.spy(orderService);

        OrderStatus orderStatus = OrderStatus.PENDING;
        List<String> pizzaNames = List.of("Margherita");
        String customerEmail = "john.doe@gmail.com";
        double orderPrice = 10;

        Pizza pizza = new Pizza("Margherita", Map.of(Size.MEDIUM, 8.5), Map.of("Tomato", 2, "Cheese", 1), "ACTIVE", 8.5
        );

        OrderRequestDto dto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);
        OrderResponseDto responseDto = new OrderResponseDto(UUID.randomUUID(), orderStatus, List.of(
                new PizzaResponseDto(
                        UUID.randomUUID(),
                        "Margherita",
                        Map.of("Tomato", 2, "Cheese", 1),
                        "ACTIVE",
                        8.5
                )
        ), orderPrice);
        Order order = new Order(UUID.randomUUID(), orderStatus, List.of(pizza), new Customer("John", customerEmail));

        Mockito.when(orderMapper.toOrder(dto)).thenReturn(order);
        Mockito.when(orderDao.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderMapper.toOrderResponseDto(order)).thenReturn(responseDto);

        OrderResponseDto result = spy.addOrder(dto);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(result, messages.getMessage(Messages.ORDER_NOT_NULL)),
                () -> Assertions.assertNotNull(result.id(), messages.getMessage(Messages.ORDER_ID_NOT_NULL)),
                () -> Assertions.assertNotNull(result.orderStatus(), messages.getMessage(Messages.ORDER_STATUS_NOT_NULL)),
                () -> Assertions.assertNotNull(result.pizzas(), messages.getMessage(Messages.ORDER_PIZZAS_NOT_NULL)),
                () -> Assertions.assertTrue(result.orderPrice()>0, messages.getMessage(Messages.ORDER_PRICE_NOT_NULL)),
                () -> Assertions.assertEquals(orderStatus, result.orderStatus(), messages.getMessage(Messages.ORDER_STATUS_SAME_AS_EXPECTED)),
                () -> Assertions.assertEquals(pizzaNames, result.pizzas().stream().map(PizzaResponseDto::name).toList(), messages.getMessage(Messages.ORDER_PIZZA_NAME_SAME_AS_EXPECTED)),
                () -> Assertions.assertEquals(orderPrice, result.orderPrice(), messages.getMessage(Messages.ORDER_PRICE_SAME_AS_EXPECTED))
        );
        Mockito.verify(spy, Mockito.times(1)).validateOrderRequest(Mockito.any(OrderRequestDto.class));
    }









}
