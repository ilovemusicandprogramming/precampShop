package com.precamp.shop.repository;

import com.precamp.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.product")
    List<Order> findAll();
    boolean existsByProductId(Long productId);
}
