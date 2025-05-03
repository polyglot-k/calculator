package org.example.Lv2.calculator;

import org.example.Lv2.calculator.operation.Adder;
import org.example.Lv2.calculator.operation.Divider;
import org.example.Lv2.calculator.operation.Multiplier;
import org.example.Lv2.calculator.operation.Subtractor;

import java.util.Map;

public class ArithmeticCalculator extends Calculator {
    public ArithmeticCalculator() {
        super(
                Map.of(
                        '+', new Adder(),
                        '-', new Subtractor(),
                        '*', new Multiplier(),
                        '/', new Divider()
                )
        );
    }
}
