package com.accenture.projetapipizza.controller;

import com.accenture.projetapipizza.service.PizzaService;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;
import com.accenture.projetapipizza.utils.Messages;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;
    private final MessageSourceAccessor messages;

    public PizzaController(PizzaService pizzaService, MessageSourceAccessor messages) {
        this.pizzaService = pizzaService;
        this.messages = messages;
    }


    @PostMapping("/pizza")
    public ResponseEntity<String> addPizza(@RequestBody PizzarequestDto pizzarequestDto){
        PizzaResponseDto pizzaResponseDto = pizzaService.addPizza(pizzarequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(messages.getMessage(Messages.PIZZA_ADD_IS_VALID)+" "+pizzaResponseDto);
    }

    @DeleteMapping("/Pizza/{id}")
    public ResponseEntity<String> deletePizza(@RequestHeader(name = "id")UUID uuid){
       PizzaResponseDto pizzaResponseDto = pizzaService.deletePizza(uuid);
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(messages.getMessage(Messages.PIZZA_DELETE)+" "+pizzaResponseDto);
    }

    @GetMapping
    public ResponseEntity<String> findAll(){
        List<PizzaResponseDto> listPizzaResponseDto = pizzaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listPizzaResponseDto.toString());
    }



}
