package com.precamp.shop.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.precamp.shop.common.BaseEntity;
import com.precamp.shop.domain.status.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private ProductStatus status;
    private String description;

    public static Product createProduct(String name, int price, int stockQuantity, String description) {
        Product product = new Product();

        product.name = name;
        product.price = price;
        product.stockQuantity = stockQuantity;
        product.description = description;
        product.status = ProductStatus.ACTIVE;

        return product;
    }

    public void updateProduct(String name, int price, int stockQuantity, String description) {
        if(this.status == ProductStatus.DELETED){
            throw new IllegalStateException("이미 삭제된 상품의 정보는 수정할 수 없습니다.");
        }
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    public void changeStatusToDeleted() {
        this.status = ProductStatus.DELETED;
    }

    public void decreaseStock(int stock) {
        int restStock = this.stockQuantity - stock;
        if(restStock < 0){
            throw new IllegalStateException("재고가 부족합니다");
        }
        this.stockQuantity = restStock;

        // 재고가 0이 되면 품절 처리
        if (this.stockQuantity == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void increaseStock(int stock) {
        this.stockQuantity += stock;

        // 재고가 다시 생기면 판매중으로 변경
        if (this.stockQuantity > 0 && this.status == ProductStatus.OUT_OF_STOCK) {
            this.status = ProductStatus.ACTIVE;
        }
    }
}
