package org.example.Lv3;

import org.example.Lv3.calculator.ArithmeticCalculatorLv3;
import org.example.Lv3.calculator.OperatorType;
import org.example.Lv3.calculator.exception.DividedByZeroException;
import org.example.Lv3.calculator.exception.InvalidOperandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Lv3 ArithmeticCalculator 테스트")
public class ArithmeticCalculatorLv3Test {
    private final ArithmeticCalculatorLv3 calculator = new ArithmeticCalculatorLv3();

    @DisplayName("Double 타입 덧셈, 뺄셈, 곱셈, 나눗셈 계산 테스트")
    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @CsvSource({
            "5.0, 3.0, '+', 8.0",
            "5.0, 3.0, '-', 2.0",
            "5.0, 3.0, '*', 15.0",
            "6.0, 3.0, '/', 2.0",
            "5.0, 2.5, '/', 2.0",
            "2.5, 2.5, '*', 6.25",
            "2147483648, 2147483648, '+',4294967296"
    })
    void testDoubleOperations(double operand1, double operand2, char operator, double expected) {
        Number result = calculator.calculate(operand1, operand2, OperatorType.fromChar(operator));
        assertEquals(expected, result.doubleValue(), 0.000001); // Double 비교 시 오차 고려
    }

    @DisplayName("Long 타입 덧셈, 뺄셈, 곱셈, 나눗셈 계산 테스트")
    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @CsvSource({
            "5, 3, '+', 8",
            "5, 3, '-', 2",
            "5, 3, '*', 15",
            "6, 3, '/', 2",
            "2147483648, 2147483648, '+',4294967296"
    })
    void testLongOperations(long operand1, long operand2, char operator, long expected) {
        Number result = calculator.calculate(operand1, operand2, OperatorType.fromChar(operator));
        assertEquals(expected, result.longValue());
    }


    @DisplayName("Double, Long 혼합 타입 덧셈, 뺄셈, 곱셈, 나눗셈 계산 테스트")
    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @CsvSource({
            "5, 3.5, '+', 8.5",
            "5, 3.1, '-', 1.9",
            "5, 2.5, '*', 12.5",
            "7.5, 3, '/', 2.5",
            "2147483648, 2147483648.55, '+',4294967296.55"
    })
    void testMixedOperations(double operand1, double operand2, char operator, double expected) {
        Number result = calculator.calculate(operand1, operand2, OperatorType.fromChar(operator));
        assertEquals(expected, result.doubleValue(), 0.000001); // Double 비교 시 오차 고려
    }

    @DisplayName("결과 리스트에서 첫 번째 Integer 결과값 제거 테스트")
    @ParameterizedTest(name = "입력: {0}, 제거 후: {1}")
    @CsvSource({
            "'3,4,5,6,7,8,9', '4,5,6,7,8,9'",
            "'10,20,30', '20,30'",
            "'1,2', '2'",
            "'99', ''"
    })
    void testFirstResultRemoval(String input, String expected) {
        List<Double> inputList = parseToDoubleList(input);
        List<Double> expectedList = parseToDoubleList(expected);
        calculator.setResultHistories(inputList);

        calculator.removeFirstResult();

        assertEquals(expectedList, calculator.getResultHistories());
    }


    @DisplayName("결과 리스트에서 특정 임계값보다 큰 결과 조회 테스트")
    @ParameterizedTest(name = "결과: {0}, 임계값: {1}, 예상 결과: {2}")
    @CsvSource({
            "'1.0, 2.5, 3.0, 1.5, 4.0', 2.0, '2.5, 3.0, 4.0'",
            "'10.0, 5.0, 15.0', 10.0, '15.0'",
            "'1.0, 1.0, 1.0', 0.5, '1.0, 1.0, 1.0'",
            "'1.0, 2.0, 3.0', 3.0, ''",
            "'', 1.0, ''"
    })
    void testGetResultsGreaterThan(String resultsInput, double threshold, String expectedResultsInput) {
        List<Double> initialResults = parseToDoubleList(resultsInput);
        List<Double> expectedResults = parseToDoubleList(expectedResultsInput);
        calculator.setResultHistories(initialResults);

        List<Double> actualResults = calculator.getResultsGreaterThan(threshold);

        assertEquals(expectedResults, actualResults);
    }

    @DisplayName("결과 리스트에서 long 타입 임계값보다 큰 결과 조회 테스트")
    @ParameterizedTest(name = "결과: {0}, 임계값: {1}, 예상 결과: {2}")
    @CsvSource({
            "'1.0, 2.5, 3.0', 2, '2.5, 3.0'",
            "'10.0, 5.0, 15.0', 10, '15.0'",
            "'1.0, 1.0, 1.0', 0, '1.0, 1.0, 1.0'",
            "'1.0, 2.0, 3.0', 3, ''",
            "'', 1, ''"
    })
    void testGetResultsGreaterThanWithLongThreshold(String resultsInput, long threshold, String expectedResultsInput) {
        List<Double> initialResults = parseToDoubleList(resultsInput);
        List<Double> expectedResults = parseToDoubleList(expectedResultsInput);
        calculator.setResultHistories(initialResults);

        List<Double> actualResults = calculator.getResultsGreaterThan(threshold);

        assertEquals(expectedResults, actualResults);
    }

    @DisplayName("결과 리스트에서 int 타입 임계값보다 큰 결과 조회 테스트")
    @ParameterizedTest(name = "결과: {0}, 임계값: {1}, 예상 결과: {2}")
    @CsvSource({
            "'1.0, 2.5, 3.0', 2, '2.5, 3.0'",
            "'10.0, 5.0, 15.0', 10, '15.0'",
            "'1.0, 1.0, 1.0', 0, '1.0, 1.0, 1.0'",
            "'1.0, 2.0, 3.0', 3, ''",
            "'', 1, ''"
    })
    void testGetResultsGreaterThanWithIntThreshold(String resultsInput, int threshold, String expectedResultsInput) {
        List<Double> initialResults = parseToDoubleList(resultsInput);
        List<Double> expectedResults = parseToDoubleList(expectedResultsInput);
        calculator.setResultHistories(initialResults);

        List<Double> actualResults = calculator.getResultsGreaterThan(threshold);

        assertEquals(expectedResults, actualResults);
    }
    @DisplayName("0으로 나누기 시 DividedByZeroException 발생 테스트")
    @Test
    void testDivideByZero() {
        assertThrows(DividedByZeroException.class, () -> {
            calculator.calculate(5.0, 0, OperatorType.DIVISION);
        });
    }

    @DisplayName("음수 피연산자 입력 시 InvalidOperandException 발생 테스트")
    @Test
    void testNegativeOperand() {
        assertThrows(InvalidOperandException.class, () -> {
            calculator.calculate(-5, 3, OperatorType.ADDITION);
        });
        assertThrows(InvalidOperandException.class, () -> {
            calculator.calculate(5, -3, OperatorType.SUBTRACTION);
        });
    }

    @DisplayName("결과 리스트가 null일 때 첫 번째 결과 제거 시 IllegalStateException 발생 테스트")
    @Test
    void testFirstResultRemovalWhenResultHistoriesIsNull(){
        Exception exception = assertThrows(IllegalStateException.class, calculator::removeFirstResult);
        assertEquals("삭제할 결과가 없습니다.", exception.getMessage());
    }

    private List<Double> parseToDoubleList(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Double::parseDouble)
                .toList();

    }

}