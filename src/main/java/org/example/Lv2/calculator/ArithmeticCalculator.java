package org.example.Lv2.calculator;

import org.example.Lv2.calculator.operation.Adder;
import org.example.Lv2.calculator.operation.Divider;
import org.example.Lv2.calculator.operation.Multiplier;
import org.example.Lv2.calculator.operation.Subtractor;

import java.util.Map;

public class ArithmeticCalculator extends Calculator {
    private static final char ADD_OPERATOR = '+';
    private static final char SUBTRACT_OPERATOR = '-';
    private static final char MULTIPLY_OPERATOR = '*';
    private static final char DIVIDE_OPERATOR = '/';

    public ArithmeticCalculator() {
        super(
                Map.of(
                        ADD_OPERATOR, new Adder(),
                        SUBTRACT_OPERATOR, new Subtractor(),
                        MULTIPLY_OPERATOR, new Multiplier(),
                        DIVIDE_OPERATOR, new Divider()
                )
        );
    }
}

