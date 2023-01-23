package com.mandler.marcelo.vendas.exception;

public class NotFoundOrderException extends RuntimeException{
    public NotFoundOrderException() {
        super("Pedido não encontrado.");
    }
}
