package com.precamp.shop.dto.order;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.status.OrderStatus;
import com.precamp.shop.domain.status.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "주문 목록 응답")
public record OrderListResponse(

        @Schema(description = "주문 ID", example = "1")
        Long orderId,

        @Schema(description = "상품명", example = "노트북")
        String productName,

        @Schema(description = "주문 수량", example = "2")
        int quantity,

        @Schema(description = "총 금액", example = "3000000")
        int totalPrice,

        @Schema(description = "주문 상태", example = "ORDER")
        OrderStatus status,

        @Schema(description = "주문 일시", example = "2024-02-02T10:30:00")
        LocalDateTime orderDate
) {
    public OrderListResponse(Order order) {
        this(
                order.getId(),
                order.getProduct().getName(),
                order.getOrderCount(),
                order.getProduct().getPrice() * order.getOrderCount(),
                order.getStatus(),
                order.getOrderDate()
        );
    }
}