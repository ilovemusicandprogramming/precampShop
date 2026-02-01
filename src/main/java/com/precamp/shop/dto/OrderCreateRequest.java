package com.precamp.shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderCreateRequest(

        @NotNull(message = "상품 ID는 필수입니다.")
        @Positive(message = "상품 ID는 양수여야 합니다.")
        Long productId,

        @NotNull(message = "주문 수량은 필수입니다.")
        @Min(value = 1, message = "주문 수량은 1개 이상이어야 합니다.")
        Integer orderCount
) {
}