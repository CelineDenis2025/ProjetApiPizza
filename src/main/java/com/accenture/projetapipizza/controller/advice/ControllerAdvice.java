package com.accenture.projetapipizza.controller.advice;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.exception.PizzaException;
import com.accenture.projetapipizza.utils.Messages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private final MessageSource messageSource;
    private final MessageSourceAccessor message;

    public ControllerAdvice(MessageSource messageSource, MessageSourceAccessor message) {
        this.messageSource = messageSource;
        this.message = message;
    }

    /**
     * Appelée quand une exception métier survient.
     * Renvoie un 400 BAD_REQUEST avec le message métier.
     */
    @ExceptionHandler({CustomerException.class, PizzaException.class})
    public ResponseEntity<ErrorDto> businessException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(
                java.time.LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ));
    }

    /**
     * appelée quand une entité n'est pas trouvée en base
     * @param e
     * @return une erreur 404
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage(Messages.ENTITY_NOT_FOUND_EXCEPTION));
    }


}
