package org.example.Lv3.calculator;

import org.example.Lv3.exception.ErrorMessage;
import org.example.Lv3.exception.InvalidOperandException;
import org.example.Lv3.exception.NoResultToRemoveException;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator {
    private final List<Double> resultHistories = new ArrayList<>();

    public <T extends Number> double calculate(T operand1, T operand2, OperatorType operator) {
        double op1 = operand1.doubleValue();
        double op2 = operand2.doubleValue();

        validateNonNegativeOperands(op1, op2);

        double result = operator.apply(op1, op2);
        resultHistories.add(result);
        return result;
    }

    public void removeFirstResult() {
        if (resultHistories.isEmpty()) {
            throw new NoResultToRemoveException(ErrorMessage.NO_RESULT);
        }
        resultHistories.remove(0);
    }

    public <T extends Number> List<Double> getResultsGreaterThan(T threshold) {
        return resultHistories.stream()
                .filter(result -> result > threshold.doubleValue())
                .toList();
    }
    public List<Double> getResultHistories() {
        return List.copyOf(resultHistories);
    }

    public void setResultHistories(List<Double> newResults) {
        resultHistories.clear();
        resultHistories.addAll(newResults);
    }

    private void validateNonNegativeOperands(double a, double b) {
        if (a < 0 || b < 0) {
            throw new InvalidOperandException(ErrorMessage.NEGATIVE_OPERAND);
        }
    }
}
