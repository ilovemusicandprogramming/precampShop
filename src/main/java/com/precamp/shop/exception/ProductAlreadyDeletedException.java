package com.precamp.shop.exception;

public class ProductAlreadyDeletedException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "이미 삭제된 상품의 정보는 수정할 수 없습니다.";

    public ProductAlreadyDeletedException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductAlreadyDeletedException(String message) {
        super(message);
    }

    public ProductAlreadyDeletedException(Long orderId) {
        super(String.format("이미 삭제된 상품의 정보는 수정할 수 없습니다.. (ID: %d)", orderId));
    }
}
