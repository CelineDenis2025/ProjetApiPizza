package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.mapper.CustomerMapper;
import com.accenture.projetapipizza.repository.CustomerDao;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
import com.accenture.projetapipizza.utils.Messages;
import lombok.AllArgsConstructor;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;
    private final MessageSourceAccessor messages;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null)
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_NOT_NULL));
        if (customerRequestDto.name() == null)
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_NAME_NOT_NULL));
        return null;
    }
}
