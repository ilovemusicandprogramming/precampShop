package com.precamp.shop.exception;

public class InvalidOrderQuantityException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "주문 수량은 1개 이상이어야 합니다.";

    public InvalidOrderQuantityException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidOrderQuantityException(String message) {
        super(message);
    }

    public InvalidOrderQuantityException(int orderCount) {
        super(String.format("주문 수량이 올바르지 않습니다. (입력된 수량: %d)", orderCount));
    }

    public InvalidOrderQuantityException(int orderCount, int minQuantity) {
        super(String.format("주문 수량은 %d개 이상이어야 합니다. (입력된 수량: %d)",
                minQuantity, orderCount));
    }
}
