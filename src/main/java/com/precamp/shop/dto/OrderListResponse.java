package com.precamp.shop.dto;

import com.precamp.shop.domain.Order;
import java.time.LocalDateTime;

public record OrderListResponse(
        Long orderId,
        String productName,
        int quantity,
        int totalPrice,
        LocalDateTime orderDate
) {
    public OrderListResponse(Order order) {
        this(
                order.getId(),
                order.getProduct().getName(),
                order.getOrderCount(),
                order.getProduct().getPrice() * order.getOrderCount(),
                order.getOrderDate()
        );
    }

}