package com.precamp.shop.controller;

import com.precamp.shop.common.ApiResponse;
import com.precamp.shop.dto.ProductCreateRequest;
import com.precamp.shop.dto.ProductListResponse;
import com.precamp.shop.dto.ProductPatchRequest;
import com.precamp.shop.dto.ProductResponse;
import com.precamp.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable("productId") Long productId){
        return ApiResponse.success(productService.findProduct(productId), "상품 조회 성공");
    }

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        return ApiResponse.success(productService.createProduct(request), "상품 등록 완료");
    }

    @PatchMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable("productId") Long productId,
                                                      @RequestBody @Valid ProductPatchRequest request) {
        return ApiResponse.success(productService.updateProduct(productId, request),"상품 수정 완료");
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ApiResponse.success("상품 삭제 완료");
    }
}
