package org.example.Lv3.calculator.exception;

public class InvalidOperandException extends IllegalArgumentException{
    public InvalidOperandException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
