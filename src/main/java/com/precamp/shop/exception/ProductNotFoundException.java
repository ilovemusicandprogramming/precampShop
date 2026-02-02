package com.precamp.shop.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "검색하신 상품은 존재하지 않습니다.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId) {
        super(String.format("상품을 찾을 수 없습니다. (ID: %d)", productId));
    }
}
