package com.precamp.shop.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "검색하신 주문은 존재하지 않습니다.";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        super(String.format("주문을 찾을 수 없습니다. (ID: %d)", orderId));
    }
}
