package org.example.Lv2.calculator.operation;

public class Adder implements Operation {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}
