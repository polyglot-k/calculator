package org.example.Lv1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Lv1App 테스트")
public class Lv1AppTest {
    @DisplayName("정수 덧셈, 뺄셈, 곱셈, 나눗셈 계산 테스트")
    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @CsvSource({
            "5, 3, '+', 8",
            "5, 3, '-', 2",
            "5, 3, '*', 15",
            "6, 3, '/', 2"
    })
    void testBasicOperations(int operand1, int operand2, char operator, int expected) {
        int result = Lv1App.calculate(operand1, operand2, operator);
        assertEquals(expected, result);
    }

    @DisplayName("0으로 나누기 시 ArithmeticException 발생 테스트")
    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Lv1App.calculate(5, 0, '/');
        });
        assertEquals("0으로 나눌 수 없습니다.", exception.getMessage());
    }

    @DisplayName("지원되지 않는 연산자 입력 시 IllegalStateException 발생 테스트")
    @Test
    void testInvalidOperator() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Lv1App.calculate(5, 3, '%');
        });
        assertEquals("지원되지 않는 연산자 입니다.%", exception.getMessage());
    }

    @DisplayName("음수 피연산자 입력 시 IllegalArgumentException 발생 테스트")
    @Test
    void testNegativeOperand() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lv1App.calculate(-5, 3, '+');
        });
        assertEquals("0 이상의 정수만 허용됩니다.", exception.getMessage());
    }
}