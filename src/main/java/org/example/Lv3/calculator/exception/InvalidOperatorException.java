package org.example.Lv3.calculator.exception;

public class InvalidOperatorException extends IllegalArgumentException{
    public InvalidOperatorException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
