package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.mapper.CustomerMapper;
import com.accenture.projetapipizza.model.Customer;
import com.accenture.projetapipizza.repository.CustomerDao;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;
    private final MessageSourceAccessor messages;
    private final String REGEX_EMAIl = "^(?!.*\\.\\.)(?!.*@.*@)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


    /** {@inheritDoc} */
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        verify(customerRequestDto);
        log.info("Validating and creating customer: '{}'", customerRequestDto.name());
        Customer saved = customerDao.save(customerMapper.toCustomer(customerRequestDto));
        log.info("Customer '{}' successfully created with id={}", saved.getName(), saved.getId());
        return customerMapper.toCustomerResponseDto(saved);
    }

    /**
     * {@inheritDoc}
     */
    public void verify(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null) {
            log.warn("Customer validation failed: request is null");
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_NOT_NULL));
        }
        if (customerRequestDto.name() == null) {
            log.warn("Customer validation failed: name is null");
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_NAME_NOT_NULL));
        }
        if (customerRequestDto.email() == null) {
            log.warn("Customer validation failed: email is null");
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_NULL));
        }
        if (!customerRequestDto.email().matches(REGEX_EMAIl)) {
            log.warn("Customer validation failed: invalid email '{}'", customerRequestDto.email());
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_VALID));
        }
    }

}
