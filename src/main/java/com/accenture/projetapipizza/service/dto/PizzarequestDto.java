package com.accenture.projetapipizza.service.dto;

import com.accenture.projetapipizza.model.Size;
import java.util.Map;

public record PizzarequestDto(   String  name,
         Map<Size,Double> sizePizzaPossible,
         Map<String , Integer> listIngedient,
         String active,
         String sizePizza,
         double price

) {


}
