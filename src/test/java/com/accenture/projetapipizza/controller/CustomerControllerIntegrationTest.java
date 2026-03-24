package com.accenture.projetapipizza.controller;
import com.accenture.projetapipizza.mapper.CustomerMapper;
import com.accenture.projetapipizza.service.CustomerServiceImpl;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
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
import java.util.UUID;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerIntegrationTest {

    private static final String API_CUSTOMERS_ENDPOINT = "/customers";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerServiceImpl customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerMapper  customerMapper;

    @Test
    @DisplayName("Test to persist customer into the posgres database")
    void testPersistCustomerSuccess() throws Exception {
        String name = "John";
        String email = "john.doe@gmail.com";

        CustomerRequestDto requestDto = new CustomerRequestDto(name, email);
        CustomerResponseDto responseDto = new CustomerResponseDto(UUID.randomUUID(), name, email);

        Mockito.when(customerService.addCustomer(Mockito.any(CustomerRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post(API_CUSTOMERS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
    
}
