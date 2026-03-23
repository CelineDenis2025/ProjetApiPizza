package com.accenture.projetapipizza.service.dto;

import com.accenture.projetapipizza.utils.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDto(

        @NotBlank(message = Messages.CUSTOMER_NAME_NOT_NULL)
        String name,

        @NotBlank(message = Messages.CUSTOMER_EMAIL_NOT_NULL)
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = Messages.CUSTOMER_EMAIL_NOT_VALID)
        String email
) {

}
