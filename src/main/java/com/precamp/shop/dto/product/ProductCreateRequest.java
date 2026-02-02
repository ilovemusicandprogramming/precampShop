package com.precamp.shop.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "상품 생성 요청")
public record ProductCreateRequest(

        @Schema(description = "상품명", example = "노트북", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "상품명은 필수입니다.")
        @Size(max = 100, message = "상품명은 100자 이내여야 합니다.")
        String name,

        @Schema(description = "가격", example = "1500000", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "가격은 필수입니다.")
        @Positive(message = "가격은 0보다 커야 합니다.")
        Integer price,

        @Schema(description = "재고 수량", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "재고 수량은 필수입니다.")
        @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
        Integer stockQuantity,

        @Schema(description = "상품 설명", example = "고성능 업무용 노트북입니다.")
        @Size(max = 500, message = "상품 설명은 500자 이내여야 합니다.")
        String description
) {
}