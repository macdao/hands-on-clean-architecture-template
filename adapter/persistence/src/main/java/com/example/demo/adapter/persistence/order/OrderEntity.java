package com.example.demo.adapter.persistence.order;

import com.example.demo.domain.order.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    private String id;

    private String buyerId;
    private String productId;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal price;
    private Instant createdDate;
    private Instant lastModifiedDate;
}
