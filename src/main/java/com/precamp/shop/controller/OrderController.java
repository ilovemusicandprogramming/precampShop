package com.precamp.shop.controller;

import com.precamp.shop.common.ApiResponse;
import com.precamp.shop.domain.Order;
import com.precamp.shop.domain.Product;
import com.precamp.shop.dto.*;
import com.precamp.shop.service.OrderService;
import com.precamp.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderListResponse>> getOrders(){
        return ApiResponse.success(orderService.findOrders(), "주문 목록 조회 성공");
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable("orderId") Long orderId){
        return ApiResponse.success(orderService.findOrder(orderId), "주문 상세 조회 성공");
    }

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        return ApiResponse.success(orderService.createOrder(request),"주문 완료");
    }

    @PatchMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable("orderId") Long orderId,
                                                  @RequestBody @Valid OrderUpdateRequest request) {
        return ApiResponse.success(orderService.updateOrder(orderId, request),"주문 변경 완료");
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ApiResponse.success("주문 취소 완료");
    }
}
