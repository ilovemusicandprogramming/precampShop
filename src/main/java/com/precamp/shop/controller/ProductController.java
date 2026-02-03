package com.precamp.shop.controller;

import com.precamp.shop.common.ApiResponse;
import com.precamp.shop.dto.product.ProductCreateRequest;
import com.precamp.shop.dto.product.ProductListResponse;
import com.precamp.shop.dto.product.ProductPatchRequest;
import com.precamp.shop.dto.product.ProductResponse;
import com.precamp.shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "상품", description = "상품 관리 API")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "상품 목록 조회(페이징 적용)")
    public ApiResponse<List<ProductListResponse>> getProducts(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                              @RequestParam(value = "limit", defaultValue = "100") int limit){
        return ApiResponse.success(productService.findProducts(offset, limit), "상품 목록 조회 성공");
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회")
    public ApiResponse<ProductResponse> getProduct(@Parameter(description = "상품 ID", example = "1", required = true)
                                                   @PathVariable("productId") Long productId){
        return ApiResponse.success(productService.findProduct(productId), "상품 조회 성공");
    }

    @PostMapping
    @Operation(summary = "상품 등록")
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        return ApiResponse.success(productService.createProduct(request), "상품 등록 완료");
    }

    @PatchMapping("/{productId}")
    @Operation(summary = "상품 수정")
    public ApiResponse<ProductResponse> updateProduct(@Parameter(description = "상품 ID", example = "1", required = true)
                                                      @PathVariable("productId") Long productId,
                                                      @RequestBody @Valid ProductPatchRequest request) {
        return ApiResponse.success(productService.updateProduct(productId, request),"상품 수정 완료");
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제")
    public ApiResponse<Void> deleteProduct(@Parameter(description = "상품 ID", example = "1", required = true)
                                           @PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ApiResponse.success("상품 삭제 완료");
    }
}