package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;

public interface CustomerService {

    /**
     * Adds a new customer after validating the provided data.
     *
     * @param customerRequestDto the information of the customer to create
     * @return the created customer as a CustomerResponseDto
     * @throws CustomerException if the provided data is invalid
     */
    public CustomerResponseDto addCustomer (CustomerRequestDto customerRequestDto);

    /**
     * Checks whether the customer data is valid.
     *
     * @param customerRequestDto the customer information to validate
     * @throws CustomerException if a field is missing or if the email is invalid
     */
    void validateCustomerRequest(CustomerRequestDto customerRequestDto);
}
