package com.mandler.marcelo.vendas.rest;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter
    List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String messageErrors) {
        this.errors = Arrays.asList(messageErrors);
    }
}
