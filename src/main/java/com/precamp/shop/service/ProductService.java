package com.precamp.shop.service;

import com.precamp.shop.domain.Product;
import com.precamp.shop.dto.ProductListResponse;
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
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("검색하신 상품은 존재하지 않습니다."));
    }
}
