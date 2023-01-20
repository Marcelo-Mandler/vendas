package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.exception.BusinessRulesException;
import com.mandler.marcelo.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRulesException(BusinessRulesException businessEx) {
        String messageErrors = businessEx.getMessage();
        return new ApiErrors(messageErrors);
    }
}
