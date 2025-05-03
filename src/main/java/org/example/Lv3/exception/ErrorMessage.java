package org.example.Lv3.exception;

public enum ErrorMessage {
    NEGATIVE_OPERAND("0 이상의 정수만 허용됩니다."),
    DIVIDE_BY_ZERO("0으로 나눌 수 없습니다."),
    INVALID_OPERATOR("유효하지 않은 연산자입니다: %s"),
    NO_RESULT("삭제할 결과가 없습니다.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
