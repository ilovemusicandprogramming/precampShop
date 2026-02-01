package com.precamp.shop.dto;

import jakarta.validation.constraints.*;

public record ProductCreateRequest(

        @NotBlank(message = "상품명은 필수입니다.")
        @Size(max = 100, message = "상품명은 100자 이내여야 합니다.")
        String name,

        @NotNull(message = "가격은 필수입니다.")
        @Positive(message = "가격은 0보다 커야 합니다.")
        Integer price,

        @NotNull(message = "재고 수량은 필수입니다.")
        @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
        Integer stockQuantity,

        @Size(max = 500, message = "상품 설명은 500자 이내여야 합니다.")
        String description
) {
}