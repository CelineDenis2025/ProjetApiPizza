package com.accenture.projetapipizza.mapper;

import com.accenture.projetapipizza.model.Customer;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerRequestDto customerRequestDto);
    CustomerResponseDto toCustomerResponseDto(Customer customer);
}
