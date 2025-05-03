package org.example.Lv3.exception;

public class DividedByZeroException extends ArithmeticException{
    public DividedByZeroException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
