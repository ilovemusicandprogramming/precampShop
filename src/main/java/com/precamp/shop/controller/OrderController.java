package com.precamp.shop.controller;

import com.precamp.shop.common.ApiResponse;
import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.dto.OrderListResponse;
import com.precamp.shop.dto.OrderResponse;
import com.precamp.shop.dto.ProductListResponse;
import com.precamp.shop.dto.ProductResponse;
import com.precamp.shop.service.OrderService;
import com.precamp.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderListResponse>> getOrders(){
        return ApiResponse.success(orderService.findOrders().stream()
                .map(OrderListResponse::new)
                .toList(), "주문 목록 조회 성공");
    }

    @GetMapping("/id")
    public ApiResponse<OrderResponse> getOrder(@PathVariable("id") Long id){
        return ApiResponse.success(OrderResponse.from(orderService.findOrder(id)), "주문 상세 조회 성공");
    }
}
