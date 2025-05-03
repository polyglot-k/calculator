package org.example.Lv2.calculator.operation;

public class Adder implements Operation {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
