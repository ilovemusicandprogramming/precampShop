package com.precamp.shop.dto;

import com.precamp.shop.domain.Product;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        int price,
        int stockQuantity,
        LocalDateTime createdAt
) {
    public ProductResponse(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCreatedAt()
        );
    }

    public static ProductResponse from(Product product) {
        return new ProductResponse(product);
    }
}