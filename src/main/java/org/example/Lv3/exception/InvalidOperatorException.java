package org.example.Lv3.exception;

public class InvalidOperatorException extends IllegalArgumentException{
    public InvalidOperatorException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
