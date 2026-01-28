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

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable("id") Long id){
        return ApiResponse.success(orderService.findOrder(id), "주문 상세 조회 성공");
    }

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        return ApiResponse.success(orderService.createOrder(request.productId(), request.orderCount()),"주문 완료");
    }

    @PatchMapping("/{id}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable("id") Long id,
                                                  @RequestBody @Valid OrderRequest request) {
        return ApiResponse.success(orderService.updateOrder(id, request.orderCount()),"주문 변경 완료");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteProduct(id);
        return ApiResponse.success("주문이 취소 되었습니다");
    }
}
