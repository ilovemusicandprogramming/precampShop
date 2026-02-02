package com.precamp.shop.exception;

public class OrderAlreadyCancelledException extends BusinessException {
    private static final String DEFAULT_MESSAGE = "이미 취소된 주문입니다.";

    public OrderAlreadyCancelledException() {
        super(DEFAULT_MESSAGE);
    }
    public OrderAlreadyCancelledException(String message) {
        super(message);
    }

    public OrderAlreadyCancelledException(Long orderId) {
        super(String.format("이미 취소된 주문입니다. (ID: %d)", orderId));
    }
}