package com.precamp.shop.exception;

public class InvalidOrderCountException extends BusinessException {
    public InvalidOrderCountException(String message) {
        super(message);
    }
}