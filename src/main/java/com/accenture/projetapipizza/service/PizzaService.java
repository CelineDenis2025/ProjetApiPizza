package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;

import java.util.List;
import java.util.UUID;

public interface PizzaService {

    PizzaResponseDto addPizza(PizzarequestDto pizzarequestDto);

    PizzaResponseDto deletePizza(UUID uuid);

    List<PizzaResponseDto> findAll();
}
