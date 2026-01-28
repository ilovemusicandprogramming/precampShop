package com.precamp.shop.dto;

import com.precamp.shop.domain.Order;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Long productId,
        String productName,
        int count,
        int orderPrice,
        LocalDateTime orderDate
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getOrderCount(),
                order.getOrderPrice(),
                order.getOrderDate()
        );
    }
}