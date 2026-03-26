package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.mapper.OrderMapper;
import com.accenture.projetapipizza.model.OrderStatus;
import com.accenture.projetapipizza.service.OrderServiceImpl;
import com.accenture.projetapipizza.service.dto.OrderRequestDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerIntegrationTest {

    private static final String API_ORDERS_ENDPOINT = "/orders";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderServiceImpl  orderService;

    @MockitoBean
    private OrderMapper orderMapper;


    @Test
    @DisplayName("Test to persist order into the posgres database")
    void testPersistOrderSuccess() throws Exception {

        OrderStatus orderStatus = OrderStatus.PENDING;
        List<String> pizzaNames = List.of("Margherita");
        String customerEmail = "john.doe@gmail.com";
        double orderPrice = 10;

        OrderRequestDto requestDto = new OrderRequestDto(orderStatus, pizzaNames, customerEmail);

        OrderResponseDto responseDto = new OrderResponseDto(
                UUID.randomUUID(),
                orderStatus,
                List.of(new PizzaResponseDto(UUID.randomUUID(), "Margherita", Map.of("Tomato", 2, "Cheese", 1), "ACTIVE", 8.5)),
                orderPrice);

        Mockito.when(orderService.addOrder(Mockito.any(OrderRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(API_ORDERS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}







