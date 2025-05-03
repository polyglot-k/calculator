package org.example.Lv2;

import org.example.Lv2.calculator.ArithmeticCalculator;
import org.example.Lv2.calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Lv2ArithmeticCalculatorTest {
    private Calculator calculator;
    @BeforeEach
    void setUp(){
        calculator = new ArithmeticCalculator();
    }
    @DisplayName("덧셈, 뺄셈, 곱셈, 나눗셈 계산 테스트")
    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @CsvSource({
            "5, 3, '+', 8",
            "5, 3, '-', 2",
            "5, 3, '*', 15",
            "6, 3, '/', 2"
    })
    void testBasicOperations(int operand1, int operand2, char operator, int expected) {
        int result = calculator.calculate(operand1, operand2, operator);
        assertEquals(expected, result);
    }
    @DisplayName("결과 리스트에서 첫 번째 결과값 제거 테스트 (Integer)")
    @ParameterizedTest(name = "입력: {0}, 제거 후: {1}")
    @CsvSource({
            "'3,4,5,6,7,8,9', '4,5,6,7,8,9'",
            "'10,20,30', '20,30'",
            "'1,2', '2'",
            "'99', ''"
    })
    void testFirstResultRemoval(String input, String expected) {
        List<Integer> inputList = parseToIntegerList(input);
        List<Integer> expectedList = parseToIntegerList(expected);
        calculator.setResultHistories(inputList);

        calculator.removeFirstResult();

        assertEquals(expectedList, calculator.getResultHistories());
    }


    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(5, 0, '/');
        });
        assertEquals("0으로 나눌 수 없습니다.", exception.getMessage());
    }

    @Test
    void testInvalidOperator() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            calculator.calculate(5, 3, '%');
        });
        assertEquals("지원되지 않는 연산자 입니다.%", exception.getMessage());
    }

    @Test
    void testNegativeOperand() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(-5, 3, '+');
        });
        assertEquals("0 이상의 정수만 허용됩니다.", exception.getMessage());
    }

    @Test
    void testFirstResultRemovalWhenResultHistoriesIsNull(){
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            calculator.removeFirstResult();
        });
        assertEquals("삭제할 결과가 없습니다.", exception.getMessage());
    }

    private List<Integer> parseToIntegerList(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }


}
