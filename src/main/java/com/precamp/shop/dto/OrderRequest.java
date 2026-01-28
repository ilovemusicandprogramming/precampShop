package com.precamp.shop.dto;

import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record OrderRequest(
        Long productId,
        int orderCount
) {
}
