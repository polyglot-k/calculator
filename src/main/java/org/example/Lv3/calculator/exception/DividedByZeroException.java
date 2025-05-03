package org.example.Lv3.calculator.exception;

public class DividedByZeroException extends ArithmeticException{
    public DividedByZeroException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
