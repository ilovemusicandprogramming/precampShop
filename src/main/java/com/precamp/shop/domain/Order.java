package com.precamp.shop.domain;

import com.precamp.shop.common.BaseEntity;
import com.precamp.shop.domain.status.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    private int orderCount;
    private int orderPrice;
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order createOrder(Product product, int orderCount) {
        // 수량 검증 후 재고 관리
        product.decreaseStock(orderCount);

        Order order = new Order();

        order.orderCount = orderCount;
        order.product = product;
        order.orderPrice = product.getPrice();
        order.orderDate = LocalDateTime.now();
        order.status = OrderStatus.ORDER;

        return order;
    }

    public void updateOrder(int orderCount) {
        this.orderCount = orderCount;
    }

    public void changeStatusToDeleted() {
        this.status = OrderStatus.CANCEL;
    }
}
