package com.precamp.shop.exception;

public class ProductCannotBeDeletedException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "삭제할 수 없는 상품입니다.";

    public ProductCannotBeDeletedException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductCannotBeDeletedException(String message) {
        super(message);
    }

    public ProductCannotBeDeletedException(Long productId) {
        super(String.format("상품을 삭제할 수 없습니다. (ID: %d)", productId));
    }
}