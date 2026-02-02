package com.precamp.shop.dto;

import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        int price,
        int stockQuantity,
        ProductStatus status,
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