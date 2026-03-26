package com.accenture.projetapipizza.controller;



import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;
import com.accenture.projetapipizza.utils.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
public class PizzaControllerIntegrationTestEndToENd {

    private static final String API_PIZZA_ADD_ENDPOINT = "/pizzas/pizza";
    private static final String API_PIZZA_FINDALL_ENDPOINT = "/pizzas";
    private static final String API_PIZZA_DELETE = "/pizzas/pizza/";

    @Autowired
    private PizzaController pizzaController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessageSourceAccessor messages;

    @Test
    @DisplayName("test of the failure of adding a pizza")
    void testAddPizzaIntegrationFail() {
        Map<String, Integer> listIngredients = new HashMap<>();
        PizzarequestDto pizzarequestDto = new PizzarequestDto(null,null, null, null, 0);

        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_PIZZA_ADD_ENDPOINT,pizzarequestDto, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(), messages.getMessage(Messages.PIZZA_BAD_REQUEST));

    }

    @Test
    @DisplayName("test of the failure of adding a pizza with null name")
    void testAddPizzaNameIntegrationFail(){
        Map<String, Integer> listIngredients = new HashMap<>();
        PizzarequestDto pizzarequestDto = new PizzarequestDto(null, listIngredients, "true", "small", 10);

        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_PIZZA_ADD_ENDPOINT,pizzarequestDto, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(), messages.getMessage(Messages.PIZZA_BAD_REQUEST));

    }

    @Test
    @DisplayName("test of the failure of adding a pizza with a null ingredients list")
    void testPizzaListIngredientIntegrationFail(){
        PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",null, "true", "small", 10);

        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_PIZZA_ADD_ENDPOINT,pizzarequestDto, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(), messages.getMessage(Messages.PIZZA_BAD_REQUEST));
    }

    @Test
    @DisplayName("test of the failure of adding a pizza with null active")
    void testPizzaActiveIntegrationFail(){
        Map<String, Integer> listIngredients = new HashMap<>();
        PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",listIngredients, null, "small", 10);

        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_PIZZA_ADD_ENDPOINT,pizzarequestDto, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(), messages.getMessage(Messages.PIZZA_BAD_REQUEST));
    }

    @Test
    @DisplayName("test of the failure of adding a pizza with null size")
    void testPizzaSizePizzaIntegrationTestFail(){
        Map<String, Integer> listIngredients = new HashMap<>();
        PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",listIngredients, "true", null, 10);

        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_PIZZA_ADD_ENDPOINT,pizzarequestDto, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(), messages.getMessage(Messages.PIZZA_BAD_REQUEST));
    }

    @Test
    @DisplayName("test of the failure of adding a pizza with null price")
    void testPizzaPriceIntegrationFail(){
        Map<String, Integer> listIngredients = new HashMap<>();
        PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",listIngredients, "true", "small", 0);

        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:" + port + API_PIZZA_ADD_ENDPOINT,pizzarequestDto, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode(), messages.getMessage(Messages.PIZZA_BAD_REQUEST));
    }

    @Test
    @DisplayName("test pizza integration")
    void testPizzaIntegration(){
        Map<String, Integer> listIngredients = new HashMap<>();
        PizzarequestDto pizzarequestDto = new PizzarequestDto("margherita",listIngredients, "true", "small", 10);
        // laaaaa
        ResponseEntity<Void> response = restTemplate
                .postForEntity("http://localhost:"+port+API_PIZZA_ADD_ENDPOINT,pizzarequestDto,Void.class);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode(),messages.getMessage(Messages.PIZZA_ADD_IS_VALID));
    }


    @Test
    @DisplayName("pizza recovery test")
    void testPizzaFindAllIntegration(){

        var raw = restTemplate.getForEntity(API_PIZZA_FINDALL_ENDPOINT, String.class);
        System.out.println("STATUS=" + raw.getStatusCode());
        System.out.println("CT=" + raw.getHeaders().getContentType());
        System.out.println("BODY=" + raw.getBody());

        ResponseEntity<List<PizzaResponseDto>> response = restTemplate.exchange("http://localhost:" + port + API_PIZZA_FINDALL_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<PizzaResponseDto>>() {
        });
        List<PizzaResponseDto> pizza = response.getBody();

        Assertions.assertAll(
                            () ->     Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
                            () ->       Assertions.assertNotNull(pizza),
                            () ->       Assertions.assertNotNull(response.getBody()),
                            () ->  Assertions.assertEquals("margherita", pizza.getFirst().name())
        );
    }

    @Test
    @DisplayName("test of deleting a pizza")
    void testPizzaDeleteIntegration(){
        UUID uuid = UUID.fromString("5fcdefb3-c90f-4b26-a30b-20d3a2cc7750");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+API_PIZZA_ADD_ENDPOINT+uuid,String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode(),messages.getMessage(Messages.PIZZA_ADD_IS_VALID));
    }
}


