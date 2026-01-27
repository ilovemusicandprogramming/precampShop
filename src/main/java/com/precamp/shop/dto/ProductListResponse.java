package com.precamp.shop.dto;

import com.precamp.shop.domain.Product;

public record ProductListResponse(
        Long id,
        String name,
        int price,
        int stockQuantity
) {

    public ProductListResponse(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }

    public static ProductListResponse from(Product product) {
       return new ProductListResponse(product);
    }
}