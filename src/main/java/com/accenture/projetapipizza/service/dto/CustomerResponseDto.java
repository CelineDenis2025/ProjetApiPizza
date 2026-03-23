package com.accenture.projetapipizza.service.dto;

import java.util.UUID;

public record CustomerResponseDto(

        UUID uuid,
        String name,
        String email
) {
}
