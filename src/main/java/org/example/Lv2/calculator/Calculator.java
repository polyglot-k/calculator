package org.example.Lv2.calculator;

import org.example.Lv2.calculator.operation.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Calculator {
    private final Map<Character, Operation> operationMap;
    private final List<Integer> resultHistories;

    public Calculator(Map<Character, Operation> operationMap) {
        this.operationMap = operationMap;
        this.resultHistories = new ArrayList<>();
    }

    public int calculate(int operand1, int operand2, char operator) {
        validateNonNegativeOperands(operand1, operand2);
        Operation operation = operationMap.get(operator);
        if (operation == null) {
            throw new IllegalStateException("지원되지 않는 연산자 입니다." + operator);
        }
        int result = operation.apply(operand1, operand2);
        resultHistories.add(result);
        return result;
    }
    
    public void removeFirstResult(){
        if (resultHistories.isEmpty()) {
            throw new IllegalStateException("삭제할 결과가 없습니다.");
        }
        resultHistories.remove(0);
    }
    
    public List<Integer> getResultHistories() {
        return List.copyOf(resultHistories);
    }
    
    public void setResultHistories(List<Integer> newResults) {
        resultHistories.clear();
        resultHistories.addAll(newResults);
    }

    private void validateNonNegativeOperands(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("0 이상의 정수만 허용됩니다.");
        }
    }
}
