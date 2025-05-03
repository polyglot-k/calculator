package org.example.Lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lv1App {

    private static final String PROMPT_OPERAND = "operand를 입력해주세요 : ";
    private static final String PROMPT_OPERATOR = "operator를 입력해주세요 (+ - * /) :";
    private static final String ERROR_INVALID_INPUT = "유효한 정수를 입력하세요. 다시 시도해주세요.";
    private static final String ERROR_INVALID_OPERATOR = "유효한 연산자를 입력하세요 (+ - * /).";
    private static final String ERROR_ZERO_DIVISION = "0으로 나눌 수 없습니다.";
    private static final String ERROR_NON_NEGATIVE_OPERANDS = "0 이상의 정수만 허용됩니다.";
    private static final String RETRY_PROMPT = "더 계산하시겠습니까? (exit 입력 시 종료)";
    private static final String EXIT_COMMAND = "exit";
    private static final String ERROR_UNSUPPORTED_OPERATOR = "지원되지 않는 연산자 입니다.";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                int operand1 = readOperand(scanner);
                int operand2 = readOperand(scanner);
                char operator = readOperator(scanner);

                try {
                    int result = calculate(operand1, operand2, operator);
                    System.out.println("결과 : " + result);
                } catch (RuntimeException e) {
                    System.out.println("에러 : " + e.getMessage());
                }
            } while (isRetries(scanner));
        }
    }

    public static int calculate(int operand1, int operand2, char operator) {
        validateNonNegativeOperands(operand1, operand2);

        return switch (operator) {
            case '+' -> add(operand1, operand2);
            case '-' -> subtract(operand1, operand2);
            case '*' -> multiply(operand1, operand2);
            case '/' -> divide(operand1, operand2);
            default -> throw new IllegalStateException(ERROR_UNSUPPORTED_OPERATOR + operator);
        };
    }

    private static void validateNonNegativeOperands(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException(ERROR_NON_NEGATIVE_OPERANDS);
        }
    }

    private static int add(int operand1, int operand2) {
        return operand1 + operand2;
    }

    private static int subtract(int operand1, int operand2) {
        return operand1 - operand2;
    }

    private static int multiply(int operand1, int operand2) {
        return operand1 * operand2;
    }

    private static int divide(int operand1, int operand2) {
        if (operand2 == 0) throw new ArithmeticException(ERROR_ZERO_DIVISION);
        return operand1 / operand2;
    }

    private static int readOperand(Scanner scanner) {
        while (true) {
            System.out.print(PROMPT_OPERAND);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(ERROR_INVALID_INPUT);
            }
        }
    }

    private static char readOperator(Scanner scanner) {
        while (true) {
            System.out.print(PROMPT_OPERATOR);
            String input = scanner.next();
            if (input.length() == 1 && "+-*/".contains(input)) {
                return input.charAt(0);
            }
            System.out.println(ERROR_INVALID_OPERATOR);
        }
    }

    private static boolean isRetries(Scanner scanner) {
        System.out.println(RETRY_PROMPT);
        return !scanner.next().equalsIgnoreCase(EXIT_COMMAND);
    }
}
