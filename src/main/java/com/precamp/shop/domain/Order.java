package com.precamp.shop.domain;

import com.precamp.shop.common.BaseEntity;
import com.precamp.shop.domain.status.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
