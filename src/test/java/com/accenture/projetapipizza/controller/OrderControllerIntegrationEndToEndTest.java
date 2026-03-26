package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.model.OrderStatus;
import com.accenture.projetapipizza.service.CustomerServiceImpl;
import com.accenture.projetapipizza.service.OrderServiceImpl;
import com.accenture.projetapipizza.service.dto.*;
import com.accenture.projetapipizza.utils.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
class OrderControllerIntegrationEndToEndTest {

    private static final String API_ORDERS_ENDPOINT = "/orders";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    private MessageSourceAccessor messages;

    @Test
    @DisplayName("Create an Order failed during Post endpoint because all inputs are null")
    void testPostOrderFail() {
        OrderStatus orderStatus = null;
        List<String> pizzaNames = null;
        String customerEmail = null;

        OrderRequestDto requestDto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + API_ORDERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), messages.getMessage(Messages.ORDER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Create an Order failed during POST endpoint because orderStatus is null")
    void testPostOrderStatusNullFail() {
        OrderStatus orderStatus = null;
        List<String> pizzaNames = List.of("Margherita");
        String customerEmail = "john.doe@gmail.com";

        OrderRequestDto requestDto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + API_ORDERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), messages.getMessage(Messages.ORDER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Create an Order failed during POST endpoint because the list of the pizza is null")
    void testPostOrderListPizzaNullFail() {
        OrderStatus orderStatus = OrderStatus.PENDING;
        List<String> pizzaNames = null;
        String customerEmail = "john.doe@gmail.com";

        OrderRequestDto requestDto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + API_ORDERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), messages.getMessage(Messages.ORDER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Create an Order failed during POST endpoint because the email of the customer is null")
    void testPostOrderCustomerEmailNullFail() {
        OrderStatus orderStatus = OrderStatus.PENDING;
        List<String> pizzaNames = List.of("Margherita");
        String customerEmail = null;

        OrderRequestDto requestDto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + API_ORDERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), messages.getMessage(Messages.ORDER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Creates an Order through POST endpoint")
    void testPostCustomerSuccess() {
        OrderStatus orderStatus = OrderStatus.PENDING;
        List<String> pizzaNames = List.of("Margherita");
        String customerEmail = "john.doe@gmail.com";

        OrderRequestDto requestDto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);
        ResponseEntity<OrderResponseDto> response = restTemplate.postForEntity("http://localhost:" + port + API_ORDERS_ENDPOINT, requestDto, OrderResponseDto.class);
        OrderResponseDto responseDto = response.getBody();

        Assertions.assertAll(() -> {
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), messages.getMessage(Messages.ORDER_CREATED));
            Assertions.assertNotNull(responseDto, messages.getMessage(Messages.ORDER_DTO_RESPONSE_NOT_NULL));
            Assertions.assertNotNull(responseDto.id(), messages.getMessage(Messages.ORDER_ID_NOT_NULL));
            Assertions.assertEquals(orderStatus, responseDto.orderStatus(), messages.getMessage(Messages.ORDER_RESPONSE_STATUS_MATCH_REQUEST_STATUS));
            Assertions.assertEquals(pizzaNames, responseDto.pizzas().stream().map(PizzaResponseDto::name).toList(), messages.getMessage(Messages.ORDER_RESPONSE_PIZZA_MATCH_REQUEST_PIZZA));
        });
    }
}
