package org.example.Lv2;

import org.example.Lv2.calculator.ArithmeticCalculator;
import org.example.Lv2.calculator.Calculator;

import java.util.Scanner;

public class Lv2App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputHandler inputHandler = new InputHandler(scanner);
            Calculator calculator = new ArithmeticCalculator();
            do {
                int operand1 = inputHandler.readOperand();
                int operand2 = inputHandler.readOperand();
                char operator = inputHandler.readOperator();

                try {
                    int result = calculator.calculate(operand1, operand2, operator);
                    System.out.println("결과 : " + result);
                    System.out.println("이전 연산 결과 : " + calculator.getResultHistories().toString());
                    calculator.removeFirstResult();
                    System.out.println("첫번째 결과 삭제 후 결과 : "+ calculator.getResultHistories().toString());
                }catch (RuntimeException e){
                    System.out.println("에러 : " + e.getMessage());
                }
            } while (inputHandler.isRetry());
        }
    }
}
