package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.PizzaException;
import com.accenture.projetapipizza.mapper.PizzaMapper;
import com.accenture.projetapipizza.model.Active;
import com.accenture.projetapipizza.model.Pizza;
import com.accenture.projetapipizza.model.Size;
import com.accenture.projetapipizza.repository.PizzaDao;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;
import com.accenture.projetapipizza.utils.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PizzaSerciceTest {


    @Mock
    private PizzaMapper pizzaMapper;

    @InjectMocks
    private PizzaServiceImpl pizzaService;

    @Mock
    private PizzaDao pizzaDao;

    @Mock
    private MessageSourceAccessor messages;

    @Test
    void addPizzaTestFailName(){

    }



    @Test
    void addPizzaTest(){
        Map<Size, Double> size = new HashMap<>();
        size.put(Size.SMALL,1.0);
        size.put(Size.MEDIUM,1.5);
        size.put(Size.LARGE,2.0);
        UUID uuid = UUID.randomUUID();
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
         PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",size,listIngredient, Active.ACTIVE,"small",price);
         PizzaResponseDto pizzaResponseDto = new PizzaResponseDto(uuid,"margherita",size,listIngredient, Active.ACTIVE);
         Pizza pizza = new Pizza(uuid,"margherita",size,listIngredient, Active.ACTIVE,price);
        Mockito.when(pizzaMapper.toPizza(pizzarequestDto)).thenReturn(pizza);
        Mockito.when(pizzaDao.save(pizza)).thenReturn(pizza);
        Mockito.when(pizzaMapper.toPizzaResponseDto(pizza)).thenReturn(pizzaResponseDto);
        PizzaResponseDto pizzaresult = pizzaService.addPizza(pizzarequestDto);
        Assertions.assertEquals(pizzaResponseDto,pizzaresult);
        Mockito.verify(pizzaMapper).toPizza(pizzarequestDto);
        Mockito.verify(pizzaDao).save(pizza);
        Mockito.verify(pizzaMapper).toPizzaResponseDto(pizza);
    }

    @Test
    void addpizzaTestFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        UUID uuid = UUID.randomUUID();
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertAll(() -> Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(null)),
                             () -> Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto(null,listSize,listIngredient, Active.ACTIVE,"small",price)),messages.getMessage(Messages.PIZZA_NAME_NOT_VALID)),
                             ()  -> Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",null,listIngredient, Active.ACTIVE,"small",price)),messages.getMessage(Messages.PIZZA_SIZE_NOT_VALID)),
                             ()  -> Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listSize,null, Active.ACTIVE,"small",price)),messages.getMessage(Messages.PIZZA_LISTINGREDIENT_NOT_VALID)),
                             ()  -> Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listSize,listIngredient, null,"small",price)),messages.getMessage(Messages.PIZZA_ACTIVE_NOT_VALID)),
                             ()  -> Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listSize,listIngredient, Active.ACTIVE,null,price)),messages.getMessage(Messages.PIZZA_SIZECHOICE_NOT_VALID)),
                             ()  -> Assertions.assertThrows(PizzaException.class,() -> pizzaService.addPizza(new PizzarequestDto("margherita",listSize,listIngredient, Active.ACTIVE,"small",0)),messages.getMessage(Messages.PIZZA_PRICE_NOT_VALID))
        );
    }


}
