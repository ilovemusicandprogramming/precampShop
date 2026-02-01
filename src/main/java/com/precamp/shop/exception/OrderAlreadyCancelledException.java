package com.precamp.shop.exception;

public class OrderAlreadyCancelledException extends BusinessException {
    public OrderAlreadyCancelledException() {
        super("이미 취소된 주문입니다.");
    }
}