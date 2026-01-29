package com.precamp.shop.service;

import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;
import com.precamp.shop.dto.ProductListResponse;
import com.precamp.shop.dto.ProductRequest;
import com.precamp.shop.dto.ProductResponse;
import com.precamp.shop.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponse> findProducts() {
        return productRepository.findAllByStatusNot(ProductStatus.DELETED).stream()
                .map(ProductListResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse findProduct(Long id) {
        return ProductResponse.from(getProduct(id));
    }

    @Transactional
    public ProductResponse createProduct(String name, int price, int stockQuantity, String description) {
        Product product = Product.createProduct(name, price, stockQuantity, description);
        productRepository.save(product);
        return ProductResponse.from(product);
    }

    @Transactional
    public ProductResponse updateProduct(Long id, String name, int price, int stockQuantity, String description) {
        Product product = getProduct(id);
        product.updateProduct(name, price, stockQuantity, description);
        return ProductResponse.from(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        product.changeStatusToDeleted();
    }

    //==== 기타메서드 ====
    private Product getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("검색하신 상품은 존재하지 않습니다."));

        if (product.getStatus() == ProductStatus.DELETED) {
            throw new IllegalStateException("이미 삭제된 상품입니다.");
        }
        return product;
    }
}
