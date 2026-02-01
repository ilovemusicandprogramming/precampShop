package com.precamp.shop.service;

import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.dto.OrderCreateRequest;
import com.precamp.shop.dto.OrderListResponse;
import com.precamp.shop.dto.OrderResponse;
import com.precamp.shop.dto.OrderUpdateRequest;
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
        Product product = getProduct(request.productId());
        Order order = Order.createOrder(product, request.orderCount());
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
                .orElseThrow(() -> new IllegalStateException("검색하신 주문은 존재하지 않습니다."));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("검색하신 상품은 존재하지 않습니다."));
    }
}
