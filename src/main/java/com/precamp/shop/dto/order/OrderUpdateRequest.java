package com.precamp.shop.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "주문 수정 요청")
public record OrderUpdateRequest(

        @NotNull(message = "주문 수량은 필수입니다.")
        @Schema(description = "주문 수량", example = "3")
        @Min(value = 1, message = "주문 수량은 1개 이상이어야 합니다.")
        Integer orderCount
) {
}