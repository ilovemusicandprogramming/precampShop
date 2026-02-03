package com.precamp.shop.repository;

import com.precamp.shop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    @Query("select o from Order o join fetch o.product")
    Page<Order> findAll(Pageable pageable);
    @Query("select o from Order o join fetch o.product where o.id = :id")
    Optional<Order> findById(@Param("id") Long id);
    boolean existsByProductId(Long productId);
}