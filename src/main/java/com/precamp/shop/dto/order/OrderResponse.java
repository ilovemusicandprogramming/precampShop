package com.precamp.shop.dto.order;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.status.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "주문 응답")
public record OrderResponse(

        @Schema(description = "주문 ID", example = "1")
        Long id,

        @Schema(description = "상품 ID", example = "1")
        Long productId,

        @Schema(description = "상품명", example = "노트북")
        String productName,

        @Schema(description = "주문 수량", example = "2")
        int count,

        @Schema(description = "주문 금액", example = "1500000")
        int orderPrice,

        @Schema(description = "주문 상태", example = "ORDER")
        OrderStatus status,

        @Schema(description = "주문 일시", example = "2024-02-02T10:30:00")
        LocalDateTime orderDate
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getOrderCount(),
                order.getOrderPrice(),
                order.getStatus(),
                order.getOrderDate()
        );
    }
}