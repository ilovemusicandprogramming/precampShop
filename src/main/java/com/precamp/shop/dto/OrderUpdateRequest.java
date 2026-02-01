package com.precamp.shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 주문 수량 변경 요청 DTO
 */
public record OrderUpdateRequest(

        @NotNull(message = "주문 수량은 필수입니다.")
        @Min(value = 1, message = "주문 수량은 1개 이상이어야 합니다.")
        Integer orderCount
) {
}