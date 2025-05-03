package org.example.Lv3;

import org.example.Lv3.calculator.OperatorType;
import org.example.Lv3.calculator.exception.ErrorMessage;
import org.example.Lv3.calculator.exception.InvalidOperandException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private static final String PROMPT_OPERAND = "operand를 입력해주세요 : ";
    private static final String PROMPT_OPERATOR = "operator를 입력해주세요 (+ - * /) :";
    private static final String PROMPT_THRESHOLD = "threshold를 입력해주세요 :";
    private static final String ERROR_INVALID_INPUT = "유효한 정수를 입력하세요. 다시 시도해주세요.";
    private static final String ERROR_INVALID_OPERATOR = "유효한 연산자를 입력하세요 (+ - * /).";

    private static final String RETRY_PROMPT = "더 계산하시겠습니까? (exit 입력 시 종료)";
    private static final String EXIT_COMMAND = "exit";
    private static final String PROMPT_DELETE_FIRST_RESULT = "첫 번째 결과를 삭제하시겠습니까? (yes/no): ";
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String ERROR_INVALID_RESPONSE = "유효한 응답을 입력하세요 (yes/no).";

    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Number readOperand() {
        while (true) {
            System.out.print(PROMPT_OPERAND);
            try {
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                }else if (scanner.hasNextLong()) {
                    return scanner.nextLong();
                } else if (scanner.hasNextDouble()) {
                    return scanner.nextDouble();
                } else {
                    scanner.next();
                    throw new InputMismatchException(ERROR_INVALID_OPERATOR);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(ERROR_INVALID_INPUT);
            }
        }
    }


    public OperatorType readOperator() {
        while (true) {
            System.out.print(PROMPT_OPERATOR);
            String input = scanner.next();
            if (input.length() == 1 && "+-*/".contains(input)) {
                return OperatorType.fromChar(
                        input.charAt(0)
                );
            }
            System.out.println(ERROR_INVALID_OPERATOR);
        }
    }
    public double readThreshold() {
        while (true) {
            System.out.print(PROMPT_THRESHOLD);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(ERROR_INVALID_INPUT);
            }
        }
    }

    public boolean shouldDeleteFirstResult() {
        while (true) {
            System.out.print(PROMPT_DELETE_FIRST_RESULT);
            String response = scanner.next().toLowerCase();
            if (response.equals(YES)) {
                return true;
            } else if (response.equals(NO)) {
                return false;
            }
            System.out.println(ERROR_INVALID_RESPONSE);
        }
    }

    public boolean isRetry() {
        System.out.println(RETRY_PROMPT);
        return !scanner.next().equalsIgnoreCase(EXIT_COMMAND);
    }
}
