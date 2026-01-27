package com.precamp.shop.service;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Order findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("검색하신 주문은 존재하지 않습니다."));
    }
}
