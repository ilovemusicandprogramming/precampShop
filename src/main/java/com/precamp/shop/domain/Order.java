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
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order createOrder(Product product, int orderCount) {
        validateOrderCount(orderCount);

        // 수량 검증 후 재고 관리
        product.decreaseStock(orderCount);

        Order order = new Order();

        order.orderCount = orderCount;
        order.product = product;
        order.orderPrice = product.getPrice() * orderCount;
        order.orderDate = LocalDateTime.now();
        order.status = OrderStatus.ORDER;

        return order;
    }

    public void changeOrderCount(int newOrderCount) {
        validateOrderCount(newOrderCount);

        if (this.status == OrderStatus.CANCEL) {
            throw new IllegalStateException("취소된 주문은 수정할 수 없습니다.");
        }

        int oldCount = this.orderCount;
        int difference = newOrderCount - oldCount;

        if (difference > 0) {
            // 수량이 늘어남 → 재고를 더 차감
            int additionalCount = difference;
            product.decreaseStock(additionalCount);

        } else if (difference < 0) {
            // 수량이 줄어듦 → 재고를 복구
            int cancelledCount = oldCount - newOrderCount;
            product.increaseStock(cancelledCount);
        }

        this.orderCount = newOrderCount;
    }

    public void cancel() {
        if (this.status == OrderStatus.CANCEL) {
            throw new IllegalStateException("이미 취소된 주문입니다.");
        }

        this.status = OrderStatus.CANCEL;
        this.product.increaseStock(this.orderCount);
    }

    private static void validateOrderCount(int orderCount) {
        if (orderCount <= 0) {
            throw new IllegalArgumentException("주문 수량은 1개 이상이어야 합니다.");
        }
    }
}
