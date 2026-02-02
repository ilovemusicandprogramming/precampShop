package com.precamp.shop.dto.product;

import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "상품 응답")
public record ProductResponse(

        @Schema(description = "상품 ID", example = "1")
        Long id,

        @Schema(description = "상품명", example = "노트북")
        String name,

        @Schema(description = "상품 설명", example = "고성능 업무용 노트북입니다.")
        String description,

        @Schema(description = "가격", example = "1500000")
        int price,

        @Schema(description = "재고 수량", example = "50")
        int stockQuantity,

        @Schema(description = "상품 상태", example = "ACTIVE")
        ProductStatus status,

        @Schema(description = "생성 일시", example = "2024-02-02T10:30:00")
        LocalDateTime createdAt
) {
    public ProductResponse(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getStatus(),
                product.getCreatedAt()
        );
    }

    public static ProductResponse from(Product product) {
        return new ProductResponse(product);
    }
}