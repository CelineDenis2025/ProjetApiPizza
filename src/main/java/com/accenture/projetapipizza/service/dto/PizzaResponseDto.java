package com.accenture.projetapipizza.service.dto;


import com.accenture.projetapipizza.model.Size;
import java.util.Map;
import java.util.UUID;

public record PizzaResponseDto(

         UUID id,
         String  name,
         Map<Size,Double>sizePizzaPossible,
         Map<String , Integer>listIngedient,
         String sizePizza,
         String active,
         double price
) {
}
