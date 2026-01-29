package com.precamp.shop.repository;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStatusNot(ProductStatus status);
    Optional<Product> findByIdAndStatusNot(Long productId, ProductStatus productStatus);
}
