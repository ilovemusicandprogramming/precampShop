package com.precamp.shop.service;

import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;
import com.precamp.shop.dto.ProductCreateRequest;
import com.precamp.shop.dto.ProductListResponse;
import com.precamp.shop.dto.ProductPatchRequest;
import com.precamp.shop.dto.ProductResponse;
import com.precamp.shop.repository.ProductRepository;
import lombok.AllArgsConstructor;
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
    public ProductResponse createProduct(ProductCreateRequest request) {
        Product product = Product.createProduct(request.name(), request.price(), request.stockQuantity(), request.description());
        productRepository.save(product);
        return ProductResponse.from(product);
    }

    @Transactional
    public ProductResponse updateProduct(Long productId, ProductPatchRequest request) {
        Product product = getProduct(productId);
        product.updateProduct(request.name(), request.price(), request.stockQuantity(), request.description());
        return ProductResponse.from(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        product.changeStatusToDeleted();
    }

    //===== 기타메서드 =====
    private Product getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("검색하신 상품은 존재하지 않습니다."));

        if (product.getStatus() == ProductStatus.DELETED) {
            throw new IllegalStateException("이미 삭제된 상품입니다.");
        }
        return product;
    }
}
