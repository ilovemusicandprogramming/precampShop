package com.precamp.shop.dto;

public record ProductRequest(
         String name,
         String description,
         int price,
         int stockQuantity
) {
}
