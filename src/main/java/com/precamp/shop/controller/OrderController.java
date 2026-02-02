package com.precamp.shop.controller;

import com.precamp.shop.common.ApiResponse;
import com.precamp.shop.dto.order.OrderCreateRequest;
import com.precamp.shop.dto.order.OrderListResponse;
import com.precamp.shop.dto.order.OrderResponse;
import com.precamp.shop.dto.order.OrderUpdateRequest;
import com.precamp.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "주문", description = "주문 관리 API")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "주문 목록 조회")
    public ApiResponse<List<OrderListResponse>> getOrders(){
        return ApiResponse.success(orderService.findOrders(), "주문 목록 조회 성공");
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "주문 상세 조회")
    public ApiResponse<OrderResponse> getOrder(@Parameter(description = "주문 ID", example = "1", required = true)
                                               @PathVariable("orderId") Long orderId){
        return ApiResponse.success(orderService.findOrder(orderId), "주문 상세 조회 성공");
    }

    @PostMapping
    @Operation(summary = "주문 생성")
    public ApiResponse<OrderResponse> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        return ApiResponse.success(orderService.createOrder(request),"주문 완료");
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "주문 수정")
    public ApiResponse<OrderResponse> updateOrder(@Parameter(description = "주문 ID", example = "1", required = true)
                                                  @PathVariable("orderId") Long orderId,
                                                  @RequestBody @Valid OrderUpdateRequest request) {
        return ApiResponse.success(orderService.updateOrder(orderId, request),"주문 수정 완료");
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "주문 취소")
    public ApiResponse<Void> deleteOrder(@Parameter(description = "주문 ID", example = "1", required = true)
                                         @PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ApiResponse.success("주문 취소 완료");
    }
}