package com.fiap.lanchoneteapi.infrastructure.exceptions;

public class MercadoPagoException extends RuntimeException {
    public MercadoPagoException(String message) {
        super(message);
    }
}
