package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.PizzaException;
import com.accenture.projetapipizza.mapper.PizzaMapper;
import com.accenture.projetapipizza.model.Pizza;
import com.accenture.projetapipizza.model.Size;
import com.accenture.projetapipizza.repository.PizzaDao;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;
import com.accenture.projetapipizza.utils.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.*;

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
    void addPizzaTestNameFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto(null,listIngredient,"true","small",price)),messages.getMessage(Messages.PIZZA_NAME_NOT_VALID));
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto(" ",listIngredient, "true","small",price)),messages.getMessage(Messages.PIZZA_NAME_NOT_VALID));
    }



    @Test
    void addPizzaTestListIngredientFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",null,"true", "true",price)),messages.getMessage(Messages.PIZZA_ACTIVE_NOT_VALID));
    }




    @Test
    @DisplayName("test of failure in adding a pizza with a null name")
    void addPizzaTestNameFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto(null,listIngredient,"true","small",price)),messages.getMessage(Messages.PIZZA_NAME_NOT_VALID));
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto(" ",listIngredient, "true","small",price)),messages.getMessage(Messages.PIZZA_NAME_NOT_VALID));
    }



    @Test
    @DisplayName("test of failure of adding a pizza with a null ingredient list")
    void addPizzaTestListIngredientFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",null,"true", "true",price)),messages.getMessage(Messages.PIZZA_ACTIVE_NOT_VALID));
    }

    @Test
    void addPizzaTestActiveFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, null,"small",price)),messages.getMessage(Messages.PIZZA_ACTIVE_NOT_VALID));
    @DisplayName("fail test of adding a pizza with null active")
    void addPizzaTestActiveFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, null,"small",price)),messages.getMessage(Messages.PIZZA_ACTIVE_NOT_VALID));
    }

    @Test
    void addPizzaTestSizePizzaFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, "true",null,price)),messages.getMessage(Messages.PIZZA_SIZECHOICE_NOT_VALID));
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, "true"," ",price)),messages.getMessage(Messages.PIZZA_SIZECHOICE_NOT_VALID));
    }

    @Test
    void addPizzaTestPriceFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        Assertions.assertThrows(PizzaException.class,() -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, "true","small",0)),messages.getMessage(Messages.PIZZA_PRICE_NOT_VALID));
    }



    @Test
    void addPizzaTest(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        UUID uuid = UUID.randomUUID();
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
         PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",listIngredient, "true","small",price);
         PizzaResponseDto pizzaResponseDto = new PizzaResponseDto(uuid,"margherita",listIngredient,"small", price);
         Pizza pizza = new Pizza(uuid,"margherita",listSize,listIngredient, "true",price);
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
    @DisplayName("fail test of adding a pizza with null size")
    void addPizzaTestSizePizzaFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, "true",null,price)),messages.getMessage(Messages.PIZZA_SIZECHOICE_NOT_VALID));
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, "true"," ",price)),messages.getMessage(Messages.PIZZA_SIZECHOICE_NOT_VALID));
    }

    @Test
    @DisplayName("test of failure to add a pizza with null price")
    void addPizzaTestPriceFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        Assertions.assertThrows(PizzaException.class,() -> pizzaService.addPizza(new PizzarequestDto("margherita",listIngredient, "true","small",0)),messages.getMessage(Messages.PIZZA_PRICE_NOT_VALID));
    }



    @Test
    @DisplayName("test of adding a pizza")
    void addPizzaTest(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        UUID uuid = UUID.randomUUID();
        Map<String , Integer> listIngredient = new HashMap<>();
        double price = 10;
         PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",listIngredient, "true","small",price);
         PizzaResponseDto pizzaResponseDto = new PizzaResponseDto(uuid,"margherita",listIngredient,"small", price,"small");
         Pizza pizza = new Pizza(uuid,"margherita",listIngredient, "true",price,"small");
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
    @DisplayName("test of the failure to add a pizza")
    void addpizzaTestFail(){
        Map<Size, Double> listSize = new HashMap<>();
        listSize.put(Size.SMALL,1.0);
        listSize.put(Size.MEDIUM,1.5);
        listSize.put(Size.LARGE,2.0);
        Map<String , Integer> listIngredient = new HashMap<>();
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(null));
    }

    @Test
    @DisplayName("test of the failure of deleting a pizza")
    void deletePizzaTestFail(){
        UUID uuid = null;
        Assertions.assertThrows(PizzaException.class,() -> pizzaService.deletePizza(uuid));
    }



@Test
@DisplayName("test of the deletion of a pizza")
void deletePizzaTest() {
    UUID uuid = UUID.randomUUID();


    Map<String, Integer> ingredients = new HashMap<>();
    ingredients.put("cheese", 1);

    Pizza pizza = new Pizza();
    pizza.setId(uuid);
    pizza.setName("margherita");
    pizza.setListIngedient(ingredients); // adapte au vrai nom de ton champ
    pizza.setActive("true");
    pizza.setPrice(10.0);

    PizzaResponseDto expected = new PizzaResponseDto(
            uuid,
            "margherita",
            ingredients,
            "true",
            10.0,
            "small"
    );

    Mockito.when(pizzaDao.getReferenceById(uuid)).thenReturn(pizza);
    Mockito.when(pizzaMapper.toPizzaResponseDto(pizza)).thenReturn(expected);

    PizzaResponseDto result = pizzaService.deletePizza(uuid);

    Assertions.assertEquals(expected, result);

    Mockito.verify(pizzaDao).getReferenceById(uuid);
    Mockito.verify(pizzaDao).delete(pizza);
    Mockito.verify(pizzaMapper).toPizzaResponseDto(pizza);
    }



    @Test
    @DisplayName("test of the pizza pickup")
    void findAllPizzaTest() {

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();

        Map<Size, Double> listSize = new HashMap<>();
        Map<String, Integer> listIngredient = new HashMap<>();
        listIngredient.put("cheese", 1);

        Pizza pizza1 = new Pizza(uuid1, "margherita", listIngredient, "true", 10,"small");
        Pizza pizza2 = new Pizza(uuid2, "chorizo", listIngredient, "true", 10,"small");
        Pizza pizza3 = new Pizza(uuid3, "ananas",  listIngredient, "true", 10,"small");

        List<Pizza> pizzasFromDao = List.of(pizza1, pizza2, pizza3);

        PizzaResponseDto dto1 = new PizzaResponseDto(uuid1, "margherita", listIngredient, "true", 10,"small");
        PizzaResponseDto dto2 = new PizzaResponseDto(uuid2, "chorizo", listIngredient, "true", 10,"small");
        PizzaResponseDto dto3 = new PizzaResponseDto(uuid3, "ananas", listIngredient, "true", 10,"small");

        List<PizzaResponseDto> dtoList = List.of(dto1, dto2, dto3);

        Mockito.when(pizzaDao.findAll()).thenReturn(pizzasFromDao);
        Mockito.when(pizzaMapper.toListPizzaResponseDto(pizzasFromDao)).thenReturn(dtoList);

        List<PizzaResponseDto> result = pizzaService.findAll();

        Assertions.assertEquals(dtoList, result);

        Mockito.verify(pizzaDao).findAll();
        Mockito.verify(pizzaMapper).toListPizzaResponseDto(pizzasFromDao);
        Map<String , Integer> listIngredient = new HashMap<>();
        Assertions.assertThrows(PizzaException.class, () -> pizzaService.addPizza(null));
    }

    @Test
    void deletePizzaTestFail(){
        UUID uuid = null;
        Assertions.assertThrows(PizzaException.class,() -> pizzaService.deletePizza(uuid));
    }



@Test
void deletePizzaTest() {
    UUID uuid = UUID.randomUUID();


    Map<String, Integer> ingredients = new HashMap<>();
    ingredients.put("cheese", 1);

    Pizza pizza = new Pizza();
    pizza.setId(uuid);
    pizza.setName("margherita");
    pizza.setListIngedient(ingredients); // adapte au vrai nom de ton champ
    pizza.setActive("true");
    pizza.setPrice(10.0);

    PizzaResponseDto expected = new PizzaResponseDto(
            uuid,
            "margherita",
            ingredients,
            "true",
            10.0
    );

    Mockito.when(pizzaDao.getReferenceById(uuid)).thenReturn(pizza);
    Mockito.when(pizzaMapper.toPizzaResponseDto(pizza)).thenReturn(expected);

    PizzaResponseDto result = pizzaService.deletePizza(uuid);

    Assertions.assertEquals(expected, result);

    Mockito.verify(pizzaDao).getReferenceById(uuid);
    Mockito.verify(pizzaDao).delete(pizza);
    Mockito.verify(pizzaMapper).toPizzaResponseDto(pizza);
    }



    @Test
    void findAllPizzaTest() {

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();

        Map<Size, Double> listSize = new HashMap<>();
        Map<String, Integer> listIngredient = new HashMap<>();
        listIngredient.put("cheese", 1);

        Pizza pizza1 = new Pizza(uuid1, "margherita", listSize, listIngredient, "true", 10);
        Pizza pizza2 = new Pizza(uuid2, "chorizo", listSize, listIngredient, "true", 10);
        Pizza pizza3 = new Pizza(uuid3, "ananas", listSize, listIngredient, "true", 10);

        List<Pizza> pizzasFromDao = List.of(pizza1, pizza2, pizza3);

        PizzaResponseDto dto1 = new PizzaResponseDto(uuid1, "margherita", listIngredient, "true", 10);
        PizzaResponseDto dto2 = new PizzaResponseDto(uuid2, "chorizo", listIngredient, "true", 10);
        PizzaResponseDto dto3 = new PizzaResponseDto(uuid3, "ananas", listIngredient, "true", 10);

        List<PizzaResponseDto> dtoList = List.of(dto1, dto2, dto3);

        Mockito.when(pizzaDao.findAll()).thenReturn(pizzasFromDao);
        Mockito.when(pizzaMapper.toListPizzaResponseDto(pizzasFromDao)).thenReturn(dtoList);

        List<PizzaResponseDto> result = pizzaService.findAll();

        Assertions.assertEquals(dtoList, result);

        Mockito.verify(pizzaDao).findAll();
        Mockito.verify(pizzaMapper).toListPizzaResponseDto(pizzasFromDao);
    }



}
