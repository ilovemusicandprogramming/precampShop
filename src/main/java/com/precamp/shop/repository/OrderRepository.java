package com.precamp.shop.repository;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
