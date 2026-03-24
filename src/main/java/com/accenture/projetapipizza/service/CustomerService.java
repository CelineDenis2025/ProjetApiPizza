package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;

public interface CustomerService {

    public CustomerResponseDto addCustomer (CustomerRequestDto customerRequestDto);

    void verify(CustomerRequestDto customerRequestDto);
}
