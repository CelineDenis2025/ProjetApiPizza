package com.accenture.projetapipizza.mapper;

import com.accenture.projetapipizza.model.Pizza;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface PizzaMapper {

    Pizza toPizza(PizzaResponseDto pizzaResponseDto);

    Pizza toPizza(PizzarequestDto pizzarequestDto);

    PizzaResponseDto toPizzaResponseDto(Pizza pizza);

}
