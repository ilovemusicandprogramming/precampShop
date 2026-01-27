package com.precamp.shop.dto;

import com.precamp.shop.domain.Order;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Long productId,      // 어떤 상품을 주문했는지 ID값
        String productName,  // 상품명
        int orderPrice,      // 주문 당시 가격
        int count,           // 주문 수량
        int totalPrice,      // 총 결제 금액
        LocalDateTime orderDate
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getProduct().getPrice(),
                order.getOrderCount(),
                order.getProduct().getPrice() * order.getOrderCount(),
                order.getOrderDate()
        );
    }
}