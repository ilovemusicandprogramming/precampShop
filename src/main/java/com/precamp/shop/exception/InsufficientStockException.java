package com.precamp.shop.exception;

public class InsufficientStockException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "재고가 부족합니다.";

    public InsufficientStockException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(Long productId, int requestedQuantity, int availableStock) {
        super(String.format("재고가 부족합니다. (상품 ID: %d, 요청 수량: %d, 재고 수량: %d)",
                productId, requestedQuantity, availableStock));
    }

    public InsufficientStockException(String productName, int requestedQuantity, int availableStock) {
        super(String.format("재고가 부족합니다. (상품명: %s, 요청 수량: %d, 재고 수량: %d)",
                productName, requestedQuantity, availableStock));
    }
}