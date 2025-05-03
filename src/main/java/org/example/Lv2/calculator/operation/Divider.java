package org.example.Lv2.calculator.operation;

public class Divider implements Operation {
    private static final String DIVIDE_BY_ZERO_MESSAGE = "0으로 나눌 수 없습니다.";
    @Override
    public int apply(int a, int b) {
        if (b == 0) throw new ArithmeticException(DIVIDE_BY_ZERO_MESSAGE);
        return a / b;
    }
}
