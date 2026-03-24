package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.mapper.CustomerMapper;
import com.accenture.projetapipizza.model.Customer;
import com.accenture.projetapipizza.repository.CustomerDao;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
import com.accenture.projetapipizza.utils.Messages;
import jakarta.validation.constraints.Email;
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
    private final String REGEX_EMAIl = "^(?!.*\\.\\.)(?!.*@.*@)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


    /**
     *
     * @param customerRequestDto
     * @return
     */
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        verify(customerRequestDto);
        Customer saved = customerDao.save(customerMapper.toCustomer(customerRequestDto));
        return customerMapper.toCustomerResponseDto(saved);
    }

    public void verify(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null)
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_NOT_NULL));
        if (customerRequestDto.name() == null)
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_NAME_NOT_NULL));
        if (customerRequestDto.email() == null)
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_NULL));
        if(!customerRequestDto.email().matches(REGEX_EMAIl))
            throw new CustomerException(messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_VALID));
    }
}
