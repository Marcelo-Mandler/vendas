package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.exception.BusinessRulesException;
import com.mandler.marcelo.vendas.exception.NotFoundOrderException;
import com.mandler.marcelo.vendas.rest.ApiErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleMethodNotValidException (MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
