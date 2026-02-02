package com.precamp.shop.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "상품 수정 요청")
public record ProductPatchRequest(

        @Schema(description = "상품명", example = "게이밍 노트북")
        @Size(max = 100, message = "상품명은 100자 이내여야 합니다.")
        String name,

        @Schema(description = "가격", example = "2000000")
        @Positive(message = "가격은 0보다 커야 합니다.")
        Integer price,

        @Schema(description = "재고 수량", example = "30")
        @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
        Integer stockQuantity,

        @Schema(description = "상품 설명", example = "최신 게이밍 노트북으로 업그레이드되었습니다.")
        @Size(max = 500, message = "상품 설명은 500자 이내여야 합니다.")
        String description
) {
}