package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.service.CustomerServiceImpl;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
class CustomerControllerIntegrationEndToEndTest {

    private static final String API_CUSTOMERS_ENDPOINT = "/customers";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    private MessageSourceAccessor messages;

    @Test
    @DisplayName("Create a Customer failed during POST endpoint because name and email are null")
    void testPostCustomerFail() {
        String name = null;
        String email = null;

        CustomerRequestDto requestDto = new CustomerRequestDto(name, email);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_CUSTOMERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(),
                messages.getMessage(Messages.CUSTOMER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Create a Customer failed during POST endpoint because name is null")
    void testPostCustomerNameNullFail() {
        String name = null;
        String email = "john.doe@gmail.com";

        CustomerRequestDto requestDto = new CustomerRequestDto(name, email);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_CUSTOMERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(),
                messages.getMessage(Messages.CUSTOMER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Create a Customer failed during POST endpoint because email is null")
    void testPostCustomerEmailNullFail() {
        String name = "John";
        String email = null;

        CustomerRequestDto requestDto = new CustomerRequestDto(name, email);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_CUSTOMERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(),
                messages.getMessage(Messages.CUSTOMER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Create a Customer failed during POST endpoint because email is not valid")
    void testPostCustomerEmailInvalidFail() {
        String name = "John";
        String email = "invalid-email";

        CustomerRequestDto requestDto = new CustomerRequestDto(name, email);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_CUSTOMERS_ENDPOINT, requestDto, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(),
                messages.getMessage(Messages.CUSTOMER_BAD_REQUEST));
    }

    @Test
    @DisplayName("Creates a Customer through POST endpoint")
    void testPostCustomerSuccess() {
        String name = "John";
        String email = "john.doe@gmail.com";

        CustomerRequestDto requestDto = new CustomerRequestDto(name, email);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_CUSTOMERS_ENDPOINT, requestDto, Void.class);

        CustomerResponseDto responseDto = customerService.addCustomer(requestDto);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), messages.getMessage(Messages.CUSTOMER_CREATED));
            Assertions.assertNotNull(responseDto, messages.getMessage(Messages.CUSTOMER_DTO_RESPONSE_NOT_NULL));
            Assertions.assertNotNull(responseDto.id(), messages.getMessage(Messages.CUSTOMER_ID_NOT_NULL));
            Assertions.assertEquals(name, responseDto.name(), messages.getMessage(Messages.CUSTOMER_RESPONSE_NAME_MATCH_REQUEST_NAME));
            Assertions.assertEquals(email, responseDto.email(), messages.getMessage(Messages.CUSTOMER_RESPONSE_EMAIL_MATCH_REQUEST_EMAIL));});
    }
}
