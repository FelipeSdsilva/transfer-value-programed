package com.felipesousa.transfer_programed_value.exceptions.costumExceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
