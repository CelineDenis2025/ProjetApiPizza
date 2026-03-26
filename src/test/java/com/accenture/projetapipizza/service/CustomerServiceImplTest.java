package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.mapper.CustomerMapper;
import com.accenture.projetapipizza.model.Customer;
import com.accenture.projetapipizza.repository.CustomerDao;
import com.accenture.projetapipizza.service.dto.CustomerRequestDto;
import com.accenture.projetapipizza.service.dto.CustomerResponseDto;
import com.accenture.projetapipizza.service.dto.OrderResponseDto;
import com.accenture.projetapipizza.utils.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

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


    @Test
    @DisplayName("Test when customer is null")
    void addCustomerNull() {
        CustomerException exception = Assertions.assertThrows(CustomerException.class,
                () -> customerService.addCustomer(null));

        Assertions.assertEquals(
                messages.getMessage(Messages.CUSTOMER_NOT_NULL),
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Test when name is null")
    void addCustomerNullName() {
        CustomerRequestDto dto = new CustomerRequestDto(null, "john.doe@gmail.fr");

        CustomerException exception = Assertions.assertThrows(CustomerException.class,
                () -> customerService.addCustomer(dto));

        Assertions.assertEquals(
                messages.getMessage(Messages.CUSTOMER_NAME_NOT_NULL),
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Test when email is null")
    void addCustomerNullEmail() {
        CustomerRequestDto dto = new CustomerRequestDto("John", null);

        CustomerException exception = Assertions.assertThrows(CustomerException.class,
                () -> customerService.addCustomer(dto));

        Assertions.assertEquals(
                messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_NULL),
                exception.getMessage()
        );

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid",
            "john@",
            "@gmail.com",
            "john@gmail",
            "john@@gmail.com",
            "john@gmail..com"
    })
    @DisplayName("Test when email is invalid")
    void addCustomerInvalidEmails(String email) {
        CustomerRequestDto dto = new CustomerRequestDto("John", email);

        CustomerException exception = Assertions.assertThrows(CustomerException.class,
                () -> customerService.addCustomer(dto));

        Assertions.assertEquals(
                messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_VALID),
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Should throw exception when email already exists")
    void addCustomerEmailAlreadyExists() {
        CustomerRequestDto dto = new CustomerRequestDto("John", "john@example.com");
        Mockito.when(customerDao.existsByEmail("john@example.com"))
                .thenReturn(true);

        CustomerException exception = Assertions.assertThrows(
                CustomerException.class,
                () -> customerService.addCustomer(dto)
        );

        Assertions.assertEquals(
                messages.getMessage(Messages.CUSTOMER_EMAIL_ALREADY_EXISTS),
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Test when customer object is well peristed from valid inputs")
    void addCustomerValidInput(){
        CustomerService spy = Mockito.spy(customerService);
        String name = "John";
        String email = "john.doe@gmail.com";
        List<OrderResponseDto> orders = List.of();

        CustomerRequestDto dtoRequest = new CustomerRequestDto(name, email);
        CustomerResponseDto returnResponse = new CustomerResponseDto(UUID.randomUUID(), name, email, orders);
        Customer customer = new Customer(name, email);

        Mockito.when(customerMapper.toCustomer(dtoRequest)).thenReturn(customer);
        Mockito.when(customerDao.save(Mockito.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerMapper.toCustomerResponseDto(Mockito.any(Customer.class))).thenReturn(returnResponse);

        CustomerResponseDto result = spy.addCustomer(dtoRequest);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(result, messages.getMessage(Messages.CUSTOMER_NOT_NULL)),
                () -> Assertions.assertNotNull(result.id(), messages.getMessage(Messages.CUSTOMER_ID_NOT_NULL)),
                () -> Assertions.assertNotNull(returnResponse.name(), messages.getMessage(Messages.CUSTOMER_NAME_NOT_NULL)),
                () -> Assertions.assertNotNull(returnResponse.email(), messages.getMessage(Messages.CUSTOMER_EMAIL_NOT_NULL)),
                () -> Assertions.assertEquals(name, result.name(), messages.getMessage(Messages.CUSTOMER_NAME_SAME_AS_EXPECTED)),
                () -> Assertions.assertEquals(email, result.email(), messages.getMessage(Messages.CUSTOMER_EMAIL_SAME_AS_EXPECTED)),
                () -> Assertions.assertEquals(orders, result.orders(), messages.getMessage(Messages.CUSTOMER_LIST_ORDER_SAME_AS_EXPECTED))
        );
        Mockito.verify(spy, Mockito.times(1)).validateCustomerRequest(Mockito.any(CustomerRequestDto.class));
    }




}
