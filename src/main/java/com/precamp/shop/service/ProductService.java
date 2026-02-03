package com.precamp.shop.service;

import com.precamp.shop.domain.Product;
import com.precamp.shop.domain.status.ProductStatus;
import com.precamp.shop.dto.product.ProductCreateRequest;
import com.precamp.shop.dto.product.ProductListResponse;
import com.precamp.shop.dto.product.ProductPatchRequest;
import com.precamp.shop.dto.product.ProductResponse;
import com.precamp.shop.exception.ProductCannotBeDeletedException;
import com.precamp.shop.exception.ProductNotFoundException;
import com.precamp.shop.repository.OrderRepository;
import com.precamp.shop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponse> findProducts(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset / limit, limit);

        return productRepository.findAllByStatusNot(ProductStatus.DELETED, pageRequest).stream()
                .map(ProductListResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse findProduct(Long productId) {
        return ProductResponse.from(getProduct(productId));
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
    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        validateNoOrderHistory(productId);
        product.changeStatusToDeleted();
    }

    //===== 기타메서드 =====
    private Product getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("검색하신 상품은 존재하지 않습니다."));

        if (product.getStatus() == ProductStatus.DELETED) {
            throw new IllegalStateException("이미 삭제된 상품입니다.");
        }
        return product;
    }

    private void validateNoOrderHistory(Long productId) {
        if (orderRepository.existsByProductId(productId)) {
            throw new ProductCannotBeDeletedException("주문 내역이 있는 상품은 삭제할 수 없습니다.");
        }
    }
}
