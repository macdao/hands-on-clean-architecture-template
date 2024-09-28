package com.example.demo.domain.order;

import com.example.demo.domain.user.UserId;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private final OrderId id;
    private final UserId buyerId;
    private OrderStatus status;
    private BigDecimal price;

    public Order(UserId buyerId, BigDecimal price) {
        this(new OrderId(), buyerId, OrderStatus.CREATED, price);
    }

    public void pay() {
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order must be in CREATED state to be paid");
        }
        this.status = OrderStatus.PAID;
    }
}
