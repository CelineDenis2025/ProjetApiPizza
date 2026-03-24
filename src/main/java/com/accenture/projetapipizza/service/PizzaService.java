package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;

public interface PizzaService {

    PizzaResponseDto addPizza(PizzarequestDto pizzarequestDto);

}
