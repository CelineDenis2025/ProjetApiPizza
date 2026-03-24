package com.accenture.projetapipizza.service.dto;

import com.accenture.projetapipizza.model.Active;
import com.accenture.projetapipizza.model.Ingredients;
import com.accenture.projetapipizza.model.Size;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record PizzaResponseDto(

         UUID id,
         String  name,
         Map<Size,Double>pricePizza,
         Map<String , Integer>listIngedient,
         Active active
) {
}
