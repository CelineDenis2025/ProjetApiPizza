package com.accenture.projetapipizza.model;

import org.junit.jupiter.api.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Nested
    @DisplayName("addCustomer")
    class AddCustomerTest {

        @Test
        @DisplayName("OK")
        void testAddCustomerSuccess() {
            Assertions.assertEquals(Customer.class, customer.getName());
        }

    }


}
