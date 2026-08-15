package com.example.orderservice.exception;

public class CartServiceUnavailableException extends RuntimeException {
    public CartServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
