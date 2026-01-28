package com.precamp.shop.controller;

import com.precamp.shop.common.ApiResponse;
import com.precamp.shop.domain.Product;
import com.precamp.shop.dto.ProductListResponse;
import com.precamp.shop.dto.ProductRequest;
import com.precamp.shop.dto.ProductResponse;
import com.precamp.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<ProductListResponse>> getProducts(){
        return ApiResponse.success(productService.findProducts(), "상품 목록 조회 성공");
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable("id") Long id){
        return ApiResponse.success(productService.findProduct(id), "상품 조회 성공");
    }

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        return ApiResponse.success(productService.createProduct(request.name(), request.price(), request.stockQuantity(), request.description()),"상품 등록 완료");
    }

    @PatchMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable("id") Long id,
                                                      @RequestBody @Valid ProductRequest request) {
        return ApiResponse.success(productService.updateProduct(id, request.name(), request.price(), request.stockQuantity(), request.description()),"상품 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ApiResponse.success("게시글이 삭제되었습니다");
    }
}
