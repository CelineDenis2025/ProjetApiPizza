package com.accenture.projetapipizza.service.dto;

import java.util.UUID;

public record CustomerResponseDto(

        UUID id,
        String name,
        String email
) {
}
