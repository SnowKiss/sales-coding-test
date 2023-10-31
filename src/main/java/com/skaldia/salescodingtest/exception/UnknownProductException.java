package com.skaldia.salescodingtest.exception;

public class UnknownProductException extends RuntimeException {
    public UnknownProductException(String message) {
        super(message);
    }
}
