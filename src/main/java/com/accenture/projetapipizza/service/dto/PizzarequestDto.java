package com.accenture.projetapipizza.service.dto;

import com.accenture.projetapipizza.model.Active;
import com.accenture.projetapipizza.model.Size;
import java.util.Map;

public record PizzarequestDto(   String  name,
         Map<Size,Double> pricePizza,
         Map<String , Integer> listIngedient,
         Active active,
         String sizePizza,
         double price

) {


}
