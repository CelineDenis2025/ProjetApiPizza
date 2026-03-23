package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.mapper.CustomerMapper;
import com.accenture.projetapipizza.model.Customer;
import com.accenture.projetapipizza.repository.CustomerDao;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerService customerService;
    @Mock
    private MessageSourceAccessor messages;

    @BeforeEach
    void setUp() {
        customerDao = mock(CustomerDao.class);
        customerMapper = mock(CustomerMapper.class);
        messages = mock(MessageSourceAccessor.class);
        customerService = new CustomerServiceImpl(customerDao, customerMapper, messages);
    }

    // Test si customer == null
    @Test
    @DisplayName("Test when customer is null")
    void addCustomerNotNull() {
        Assertions.assertThrows(CustomerException.class, () -> customerService.addCustomer(null));
    }

    // Test si name == null
    @Test
    @DisplayName("Test when name is null")
    void addCustomerNotNullName() {
        CustomerRequestDto dto = new CustomerRequestDto(null, "john.doe@gmail.fr");
        Assertions.assertThrows(CustomerException.class, () -> customerService.addCustomer(dto));
    }









































    // Test si email == null
    @Test
    @DisplayName("Test when email is null")
    void addCustomerNotNullEmail() {
        CustomerRequestDto dto = new CustomerRequestDto("John", null);
        Assertions.assertThrows(CustomerException.class, () -> customerService.addCustomer(null));
    }


}
