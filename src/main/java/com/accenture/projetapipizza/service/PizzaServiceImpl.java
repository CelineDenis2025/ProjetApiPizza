package com.accenture.projetapipizza.service;

import com.accenture.projetapipizza.exception.PizzaException;
import com.accenture.projetapipizza.mapper.PizzaMapper;
import com.accenture.projetapipizza.model.Pizza;
import com.accenture.projetapipizza.model.Size;
import com.accenture.projetapipizza.repository.PizzaDao;
import com.accenture.projetapipizza.service.dto.PizzaResponseDto;
import com.accenture.projetapipizza.service.dto.PizzarequestDto;
import com.accenture.projetapipizza.utils.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.impl.Slf4jLogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaMapper pizzaMapper;
    private final PizzaDao pizzaDao;
    private final MessageSourceAccessor messages;

    public PizzaServiceImpl(PizzaMapper pizzaMapper, PizzaDao pizzaDao, MessageSourceAccessor messages) {
        this.pizzaMapper = pizzaMapper;
        this.pizzaDao = pizzaDao;
        this.messages = messages;
    }

    /**
     * ajout d'une pizza en base
     * @param pizzarequestDto
     * @return Retourne une PizzaResponseDto contenant les informations de la pizza ajouter.
     */
    @Override
    @Transactional
    public PizzaResponseDto addPizza(PizzarequestDto pizzarequestDto) {
        Map<Size, Double> size = new HashMap<>();
        size.put(Size.SMALL, 1.0);
        size.put(Size.MEDIUM, 1.5);
        size.put(Size.LARGE, 2.0);
        double multiplicateur = 1;
        if (pizzarequestDto == null || pizzarequestDto.active() == null || pizzarequestDto.listIngedient() == null ||
                pizzarequestDto.name() == null || pizzarequestDto.name().isBlank() || pizzarequestDto.sizePizza() == null || pizzarequestDto.sizePizza().isBlank()|| pizzarequestDto.price() == 0) {
            log.info(Messages.PIZZA_ELEMENT_NOT_VALID);
            throw new PizzaException(messages.getMessage(Messages.PIZZA_ELEMENT_NOT_VALID));
        }
        if (pizzarequestDto.sizePizza().equals("small") || pizzarequestDto.sizePizza().equals("medium") || pizzarequestDto.sizePizza().equals("large")){
            switch (pizzarequestDto.sizePizza()) {
                case "small" -> multiplicateur = size.get(Size.SMALL);  // a verif la bonne implémentation lors du test d'intégration
                case "medium" -> multiplicateur = size.get(Size.MEDIUM);
                case "large" -> multiplicateur = size.get(Size.LARGE);
                }
            Pizza pizza = pizzaMapper.toPizza(pizzarequestDto);
            pizza.setPrice(pizza.getPrice() * multiplicateur);
            Pizza pizzaSaved = pizzaDao.save(pizza);
            log.info(Messages.PIZZA_ADD_IS_VALID);
            return pizzaMapper.toPizzaResponseDto(pizzaSaved);
        }else{
            log.info(Messages.PIZZA_ELEMENT_NOT_VALID);
            throw new PizzaException(messages.getMessage(Messages.PIZZA_ELEMENT_NOT_VALID));
        }
    }

    /**
     * supprime une pizza en base
     * @param uuid
     * @return Retourne une PizzaResponseDto contenant les informations de la pizza supprimer.
     */

    @Override
    @Transactional
    public PizzaResponseDto deletePizza(UUID uuid) {
        if(uuid == null) {
            log.info(Messages.PIZZA_DELETE_UUID_IS_NULL);
            throw new PizzaException(messages.getMessage(Messages.PIZZA_DELETE_UUID_IS_NULL));
        }
            Pizza pizzaInDb = pizzaDao.getReferenceById(uuid);
            PizzaResponseDto pizzaResponseDto = pizzaMapper.toPizzaResponseDto(pizzaInDb);
            pizzaDao.delete(pizzaInDb);
            log.info(Messages.PIZZA_DELETE);
            return pizzaResponseDto;
    }

    /**
     * récupére les pizzas présentes dans la base
     * @return Retourne une liste de PizzaResponseDto représentant les pizzas présentes en base de données.
     */
    @Override
    public List<PizzaResponseDto> findAll() {
        List<Pizza> listPizza = pizzaDao.findAll();
        log.info(Messages.PIZZA_FINDALL);
        return pizzaMapper.toListPizzaResponseDto(listPizza);
    }


}
