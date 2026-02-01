package com.precamp.shop.dto;

import jakarta.validation.constraints.*;

public record ProductPatchRequest(

        @Size(max = 100, message = "상품명은 100자 이내여야 합니다.")
        String name,

        @Positive(message = "가격은 0보다 커야 합니다.")
        Integer price,

        @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
        Integer stockQuantity,

        @Size(max = 500, message = "상품 설명은 500자 이내여야 합니다.")
        String description
) {
}