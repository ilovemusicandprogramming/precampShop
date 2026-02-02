package com.precamp.shop.service;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.dto.order.OrderCreateRequest;
import com.precamp.shop.dto.order.OrderListResponse;
import com.precamp.shop.dto.order.OrderResponse;
import com.precamp.shop.dto.order.OrderUpdateRequest;
import com.precamp.shop.exception.OrderNotFoundException;
import com.precamp.shop.exception.ProductNotFoundException;
import com.precamp.shop.repository.OrderRepository;
import com.precamp.shop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderListResponse> findOrders() {
        return orderRepository.findAll().stream()
                .map(OrderListResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findOrder(Long id) {
        return OrderResponse.from(getOrder(id));
    }

    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request) {
        Order order = Order.createOrder(getProduct(request.productId()), request.orderCount());
        orderRepository.save(order);
        return OrderResponse.from(order);
    }

    @Transactional
    public OrderResponse updateOrder(Long id, OrderUpdateRequest request) {
        Order order = getOrder(id);
        order.changeOrderCount(request.orderCount());
        return OrderResponse.from(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = getOrder(id);
        order.cancel();
    }

    //===== 기타메서드 =====
    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findByIdWithLock(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
