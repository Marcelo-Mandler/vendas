package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.exception.BusinessRulesException;
import com.mandler.marcelo.vendas.exception.NotFoundOrderException;
import com.mandler.marcelo.vendas.rest.ApiErrors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleBusinessRulesException(BusinessRulesException businessEx) {
        String messageErrors = businessEx.getMessage();
        return new ApiErrors(messageErrors);
    }

    @ExceptionHandler(NotFoundOrderException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handleNotFoundOrderException (NotFoundOrderException nfEx) {
        return new ApiErrors(nfEx.getMessage());
    }
}
