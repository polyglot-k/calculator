package org.example.Lv3.calculator.exception;

public class NoResultToRemoveException extends IllegalStateException {
    public NoResultToRemoveException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}