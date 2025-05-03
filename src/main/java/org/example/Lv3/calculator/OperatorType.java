package org.example.Lv3.calculator;

import org.example.Lv3.exception.ErrorMessage;
import org.example.Lv3.exception.DividedByZeroException;
import org.example.Lv3.exception.InvalidOperatorException;

public enum OperatorType {
    ADDITION('+') {
        @Override
        public double apply(double operand1, double operand2) {
            return operand1 + operand2;
        }
    },
    SUBTRACTION('-') {
        @Override
        public double apply(double operand1, double operand2) {
            return operand1 - operand2;
        }
    },
    MULTIPLICATION('*') {
        @Override
        public double apply(double operand1, double operand2) {
            return operand1 * operand2;
        }
    },
    DIVISION('/') {
        @Override
        public double apply(double operand1, double operand2) {
            if (operand2 == 0) throw new DividedByZeroException(ErrorMessage.DIVIDE_BY_ZERO);
            return operand1 / operand2;
        }
    };

    private final char symbol;

    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract double apply(double operand1, double operand2);

    public static OperatorType fromChar(char symbol) {
        for (OperatorType operator : values()) {
            if (operator.getSymbol() == symbol) {
                return operator;
            }
        }
        throw new InvalidOperatorException(ErrorMessage.INVALID_OPERATOR);
    }
}
