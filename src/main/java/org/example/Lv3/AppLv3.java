package org.example.Lv3;

import org.example.Lv3.calculator.ArithmeticCalculatorLv3;
import org.example.Lv3.calculator.OperatorType;

import java.util.List;
import java.util.Scanner;

public class AppLv3 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputHandler inputHandler = new InputHandler(scanner);
            ArithmeticCalculatorLv3 calculator = new ArithmeticCalculatorLv3();

            do {
                Number operand1 = inputHandler.readOperand();
                OperatorType operator = inputHandler.readOperator();
                Number operand2 = inputHandler.readOperand();

                try {
                    double result = calculator.calculate(operand1, operand2, operator);
                    System.out.println("결과: " + result);

                    double threshold = inputHandler.readThreshold();
                    List<Double> resultsGreaterThanThreshold = calculator.getResultsGreaterThan(threshold);
                    System.out.println("임계치 (" + threshold + ") 보다 큰 결과 리스트: " + resultsGreaterThanThreshold);
                    System.out.println("이전 연산 결과 : " + calculator.getResultHistories().toString());
                    if (!inputHandler.shouldDeleteFirstResult()) continue;

                    System.out.println("이전 연산 결과 : " + calculator.getResultHistories().toString());
                    calculator.removeFirstResult();
                    System.out.println("첫번째 결과 삭제 후 결과 : "+ calculator.getResultHistories().toString());

                } catch (Exception e) {
                    System.err.println("오류 발생: " + e.getMessage());
                }
            } while (inputHandler.isRetry());

        }
    }
}