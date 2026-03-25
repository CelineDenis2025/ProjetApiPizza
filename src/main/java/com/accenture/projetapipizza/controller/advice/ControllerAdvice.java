package com.accenture.projetapipizza.controller.advice;

import com.accenture.projetapipizza.exception.CustomerException;
import com.accenture.projetapipizza.exception.PizzaException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private final MessageSource messageSource;

    public ControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
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
}
